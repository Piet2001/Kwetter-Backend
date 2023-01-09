package com.kwetter.userservice.Controllers;

import com.kwetter.userservice.Domain.Dto.ChangeUsernameDto;
import com.kwetter.userservice.Domain.Dto.UserDto;
import com.kwetter.userservice.Domain.Models.User;
import com.kwetter.userservice.Domain.Service.UserService;
import com.nimbusds.jose.shaded.json.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "User Controller")
@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasAuthority('SCOPE_User.All')")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Get user")
    @GetMapping(value = "/")
    public User getUser() {
        return new User("1", "Test User", "Test@mail.com");
    }

    @ApiOperation("Create User")
    @PostMapping("/new")
    public ResponseEntity<Object> createUser(@RequestBody UserDto dto) throws IllegalAccessException {
        Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
        User user = service.addUser(dto, authContext);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Update User")
    @PutMapping("/changeUsername")
    public ResponseEntity<Object> updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody ChangeUsernameDto dto) {
        Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
        try {
            User user = service.changeUserName(dto, authContext);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Delete user")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable String id) throws IllegalAccessException {
        Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
        if (isAdmin(authContext)) {
            if (service.deleteUser(id)) {
                return HttpStatus.valueOf(200);
            }
            return HttpStatus.BAD_REQUEST;
        }
        else return HttpStatus.FORBIDDEN;
    }

    private boolean isAdmin(Authentication authContext) throws IllegalAccessException {
        Map<?, ?> claims = (Map<?, ?>) FieldUtils.readField(authContext.getPrincipal(), "claims", true);
        JSONArray arr = (JSONArray) claims.get("roles");
        if (arr != null) {
            String role = (String) arr.get(0);
            return role.equals("Role.Admin");
        } else {
            return false;
        }
    }
}

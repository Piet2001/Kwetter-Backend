package com.kwetter.userservice.Controllers;

import com.kwetter.userservice.Domain.Dto.UserDto;
import com.kwetter.userservice.Domain.Models.User;
import com.kwetter.userservice.Domain.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "User Controller")
@RestController
@CrossOrigin(origins = "*")
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
    public ResponseEntity<Object> createUser(@RequestBody UserDto dto) {
        User user = service.addUser(dto);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Update User")
    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto dto) {
        try {
            User user = service.updateUser(dto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Delete user")
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteUser(@PathVariable String id) {
        if (service.deleteUser(id)) {
            return HttpStatus.valueOf(200);
        }
        return HttpStatus.BAD_REQUEST;
    }

}

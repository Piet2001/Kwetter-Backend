package com.kwetter.userservice.API.Controller;

import com.kwetter.userservice.Domain.Models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "User Controller")
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @ApiOperation(value = "Get user")
    @GetMapping(value = "/")
    public User getUser() {
        return new User("1", "Test User", "Test@mail.com");
    }


}

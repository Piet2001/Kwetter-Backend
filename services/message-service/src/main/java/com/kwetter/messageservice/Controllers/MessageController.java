package com.kwetter.messageservice.Controllers;


import com.kwetter.messageservice.Domain.Models.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Message Controller")
@RestController
@CrossOrigin(origins = "*")
public class MessageController {

    @ApiOperation(value = "Get Message")
    @GetMapping(value = "/")
    public Message getMessage() {
        return new Message("1","Testname","Dit is een test bericht");
    }


}

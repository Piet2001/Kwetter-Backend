package com.kwetter.messageservice.Controllers;


import com.kwetter.messageservice.Domain.Dto.MessageDto;
import com.kwetter.messageservice.Domain.Models.Message;
import com.kwetter.messageservice.Domain.Service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Message Controller")
@RestController
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService service;

    @ApiOperation(value = "Get Message")
    @GetMapping(value = "/")
    public Message getMessage() {
        return new Message("1","usid0", "Testname", "Dit is een test bericht");
    }

    @ApiOperation("Create Message")
    @PostMapping("/new")
    public ResponseEntity<Object> createMessage(@RequestBody MessageDto dto) {
        Message message = service.addMessage(dto);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create massage", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("update message")
    @PostMapping("/update")
    public ResponseEntity<Object> updateMessage(@RequestBody MessageDto dto) {
        try {
            Message message = service.updateMessage(dto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

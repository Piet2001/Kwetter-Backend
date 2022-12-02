package com.kwetter.messageservice.Controllers;


import com.kwetter.messageservice.Domain.Dto.MessageDto;
import com.kwetter.messageservice.Domain.Models.Message;
import com.kwetter.messageservice.Domain.Service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "Message Controller")
@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasAuthority('SCOPE_Message.All')")
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
    public ResponseEntity<Object> createMessage(@RequestBody MessageDto dto) throws IllegalAccessException {
        Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
        Map<?, ?> claims = (Map<?, ?>) FieldUtils.readField(authContext.getPrincipal(), "claims", true);
        String userId = claims.get("oid").toString();
        dto.setUserId(userId);
        Message message = service.addMessage(dto);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create massage", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("update message")
    @PostMapping("/update")
    public ResponseEntity<Object> updateMessage(@RequestBody MessageDto dto) throws IllegalAccessException {
        Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
        Map<?, ?> claims = (Map<?, ?>) FieldUtils.readField(authContext.getPrincipal(), "claims", true);
        String userId = claims.get("oid").toString();
        dto.setUserId(userId);
        try {
            Message message = service.updateMessage(dto);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

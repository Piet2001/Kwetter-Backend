package com.kwetter.messageservice.ServiceTests;

import com.kwetter.messageservice.Domain.Dto.MessageDto;
import com.kwetter.messageservice.Domain.Models.Message;
import com.kwetter.messageservice.Domain.Service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageServiceTest {

    @Autowired
    private MessageService service;

    @Test
    public void createShouldSave() {
        MessageDto dto = new MessageDto();
        dto.setUsername("Tester");
        dto.setMessage("Test message");

        Message message = service.addMessage(dto);

        assertNotNull(message);
    }
}

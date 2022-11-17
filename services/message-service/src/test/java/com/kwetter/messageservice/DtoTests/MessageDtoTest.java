package com.kwetter.messageservice.DtoTests;

import com.kwetter.messageservice.Domain.Dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageDtoTest {

    @Test
    public void MessageDtoNotNull() {
        MessageDto dto = new MessageDto();
        dto.setUserId("usud2");
        dto.setMessage("test");
        dto.setUsername("Test");

        System.out.println(dto);
        assertNotNull(dto);
    }
}

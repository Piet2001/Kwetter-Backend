package com.kwetter.userservice.DtoTests;

import com.kwetter.userservice.Domain.Dto.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDtoTest {

    @Test
    public void UserDtoNotNull() {
        UserDto dto = new UserDto();
        dto.setEmail("test");
        dto.setName("Test");

        System.out.println(dto);
        assertNotNull(dto);
    }
}

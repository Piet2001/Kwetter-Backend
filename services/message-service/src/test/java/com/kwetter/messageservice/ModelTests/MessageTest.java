package com.kwetter.messageservice.ModelTests;

import com.kwetter.messageservice.Domain.Models.Message;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageTest {

    @Test
    public void NameEqualsNameFromConstructor() {
        Message message = new Message("1","Test User", "Dit is een testbericht");
        assert(message.getMessage() == "Dit is een testbericht");
    }

    @Test
    public void NameNotEqualToOldAfterSet() {
        Message message = new Message("1","Test User", "Dit is een testbericht");
        message.setUsername("New Name");
        assert(message.getUsername() != "Test User");
    }

    @Test
    public void NameEqualToNewAfterSet() {
        Message message = new Message("1","Test User", "Dit is een testbericht");
        message.setUsername("New Name");
        assert(message.getUsername() == "New Name");
    }

    @Test
    public void EmailCanChange() {
        Message message = new Message("1","Test User", "Dit is een testbericht");
        message.setMessage("Dit is een nieuw testbericht");
        assert(message.getMessage() == "Dit is een nieuw testbericht");
    }

}

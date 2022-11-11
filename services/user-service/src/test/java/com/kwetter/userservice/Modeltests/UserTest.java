package com.kwetter.userservice.Modeltests;

import com.kwetter.userservice.Domain.Models.User;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest {

    @Test
    public void NameEqualsNameFromConstructor() {
        User user = new User("1","Test User", "Test@mail.com");
        assert(user.getName() == "Test User");
    }

    @Test
    public void NameNotEqualToOldAfterSet() {
        User user = new User("1","Test User", "Test@mail.com");
        user.setName("New Name");
        assert(user.getName() != "Test User");
    }

    @Test
    public void EmailCanChange() {
        User user = new User("1","Test User", "Test@mail.com");
        user.setEmail("new@mail.com");
        assert(user.getEmail() == "new@mail.com");
    }

}

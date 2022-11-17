package com.kwetter.userservice.Domain.Service;

import com.kwetter.userservice.Domain.Dto.ChangeUsernameDto;
import com.kwetter.userservice.Domain.Dto.UserDto;
import com.kwetter.userservice.Domain.Models.User;
import com.kwetter.userservice.Repository.UserRepo;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private final DaprClient daprClient = new DaprClientBuilder().build();

    public User addUser(UserDto dto) {
        User newuser = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        return repo.save(newuser);

    }

    public User changeUserName(ChangeUsernameDto dto) throws Exception {
        Optional<User> lookupUser = repo.findById(dto.getId());
        if (lookupUser.isPresent()) {
            User user = lookupUser.get();
            user.setName(dto.getName());
            daprClient.publishEvent("pubsub", "changeUsername", dto).block();
            return repo.save(user);
        } else {
            throw new Exception("User not found");
        }
    }

    public boolean deleteUser(String id) {
        Optional<User> user = repo.findById(id);
        if (user.isPresent()) {
            repo.delete(user.get());
            return true;
        }
        return false;
    }
}

package com.kwetter.userservice.Domain.Service;

import com.kwetter.userservice.Domain.Dto.ChangeUsernameDto;
import com.kwetter.userservice.Domain.Dto.UserDto;
import com.kwetter.userservice.Domain.Models.User;
import com.kwetter.userservice.Repository.UserRepo;
import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private final DaprClient daprClient = new DaprClientBuilder().build();

    public User addUser(UserDto dto, Authentication authContext) throws IllegalAccessException {
        Map<?, ?> claims = (Map<?, ?>) FieldUtils.readField(authContext.getPrincipal(), "claims", true);
        String userId = claims.get("oid").toString();
        User newuser = User.builder()
                .id(userId)
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        return repo.save(newuser);

    }

    public User changeUserName(ChangeUsernameDto dto, Authentication authContext) throws Exception {
        Map<?, ?> claims = (Map<?, ?>) FieldUtils.readField(authContext.getPrincipal(), "claims", true);
        String userId = claims.get("oid").toString();
        if (!userId.equals(dto.getId())) {
            throw new IllegalAccessException();
        }

        Optional<User> lookupUser = repo.findById(userId);
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

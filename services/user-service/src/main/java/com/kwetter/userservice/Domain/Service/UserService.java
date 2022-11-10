package com.kwetter.userservice.Domain.Service;

import com.kwetter.userservice.Domain.Dto.UserDto;
import com.kwetter.userservice.Domain.Models.User;
import com.kwetter.userservice.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public User addUser(UserDto dto) {
        User newuser = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        newuser = repo.save(newuser);
        return newuser;

    }

    public User updateUser(UserDto dto) throws Exception {
        Optional<User> lookupUser = repo.findById(dto.getId());
        if (lookupUser.isPresent()) {
            User user = User.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .build();
            user = repo.save(user);
            return user;
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

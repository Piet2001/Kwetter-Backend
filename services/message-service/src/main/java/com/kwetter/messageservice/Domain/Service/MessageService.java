package com.kwetter.messageservice.Domain.Service;

import com.kwetter.messageservice.Domain.Dto.ChangeUsernameDto;
import com.kwetter.messageservice.Domain.Dto.MessageDto;
import com.kwetter.messageservice.Domain.Models.Message;
import com.kwetter.messageservice.Repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepo repo;

    public Message addMessage(MessageDto dto) {
        Message newmessage = Message.builder()
                .message(dto.getMessage())
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .build();
        newmessage = repo.save(newmessage);
        return newmessage;
    }

    public Message updateMessage(MessageDto dto) throws Exception {
        Optional<Message> lookupMessage = repo.findById(dto.getId());
        if (lookupMessage.isPresent()) {
            Message message = Message.builder()
                    .id(dto.getId())
                    .message(dto.getMessage())
                    .username(dto.getUsername())
                    .build();
            message = repo.save(message);
            return message;
        } else {
            throw new Exception("Message not found");
        }
    }

    public boolean deleteMessage(String id) {
        Optional<Message> message = repo.findById(id);
        if (message.isPresent()) {
            repo.delete(message.get());
            return true;
        }
        return false;
    }

    public void updateUserName(ChangeUsernameDto dto) {
        List<Message> messages = repo.findAll();
        ;

        for (Message message : messages) {
            if (message.getUserId().equals(dto.getId())) {
                message.setUsername(dto.getName());
            }
        }

        repo.saveAll(messages);
    }

    public List<Message> getAllMessages() {
        return repo.findAll();
    }
}

package com.kwetter.messageservice.Repository;

import com.kwetter.messageservice.Domain.Models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends MongoRepository<Message, String> {
}

package com.kwetter.messageservice.Controllers;

import com.kwetter.messageservice.Domain.Dto.ChangeUsernameDto;
import com.kwetter.messageservice.Domain.Service.MessageService;
import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Api(tags = "PubSub Message Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pubsub")
public class PubSubController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("Get updated usernames")
    @Topic(name = "changeUsername", pubsubName = "pubsub")
    @PostMapping("/updated_username")
    public Mono<Void> updatedName(@RequestBody(required = false) CloudEvent<ChangeUsernameDto> cloudEvent) {
        return Mono.fromRunnable(() -> {
            try {
                System.out.println(cloudEvent.getData());
                messageService.updateUserName(cloudEvent.getData());

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}

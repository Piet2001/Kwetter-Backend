package com.kwetter.messageservice.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private String id;
    private String username;
    private String message;

}

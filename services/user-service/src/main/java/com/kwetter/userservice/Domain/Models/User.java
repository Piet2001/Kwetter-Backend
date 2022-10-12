package com.kwetter.userservice.Domain.Models;

import lombok.*;

@Getter
@Setter
// @Document("users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;
    private String name;

    private String email;

    // Possible expansion for profile pictures
}

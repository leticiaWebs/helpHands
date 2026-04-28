package com.helpHands.helpHands.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthentication {
    private String username;
    private String password;

}

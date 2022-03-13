package com.example.jwtdemo.user.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestUserSignInDto {
    private String username;
    private String password;
}

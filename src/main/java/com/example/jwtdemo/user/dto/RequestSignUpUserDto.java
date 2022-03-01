package com.example.jwtdemo.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestSignUpUserDto {
    private String username;
    private String password;
    private String role;
}

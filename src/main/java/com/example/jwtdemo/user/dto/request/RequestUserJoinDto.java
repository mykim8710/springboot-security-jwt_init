package com.example.jwtdemo.user.dto.request;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class RequestUserJoinDto {
    private String username;
    private String password;
    private List<Integer> authorities;
}

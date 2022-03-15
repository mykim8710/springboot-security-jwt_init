package com.example.jwtdemo.user.dto.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@ToString
public class RequestUserJoinDto {
    @NotBlank(message = "This field must not be empty.")
    private String username;

    @NotBlank(message = "This field must not be empty.")
    private String password;

    private List<Integer> authorities;
}

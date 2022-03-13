package com.example.jwtdemo.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ErrorCode {

    //Sign-up Error
    EXIST_ACCOUNT(400, "S001", "This Account is exist."),

    // Sign-in Error
    NO_FIND_USER(400, "S002", "This account does not exist."),
    NOT_MATCH_PASSWORD(400, "S003", "Passwords do not match."),

    // Validation Error
    NOT_BLANK(400, "V001", ""),
    EMAIL(400, "V002", ""),

    ;


    private int status;
    private String code;
    private String message;
}

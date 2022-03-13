package com.example.jwtdemo.user.exception;


import com.example.jwtdemo.global.error.BusinessException;
import com.example.jwtdemo.global.error.ErrorCode;

public class ExistAccountException extends BusinessException {
    public ExistAccountException() {
        super(ErrorCode.EXIST_ACCOUNT);
    }
}

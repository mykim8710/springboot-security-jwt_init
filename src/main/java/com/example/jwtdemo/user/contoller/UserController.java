package com.example.jwtdemo.user.contoller;

import com.example.jwtdemo.user.dto.RequestSignUpUserDto;
import com.example.jwtdemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/api/auth/sign-up")
    public ResponseEntity<HttpStatus> signUpUser(@RequestBody RequestSignUpUserDto requestSignUpUserDto) {
        log.info("[POST] /api/auth/sign-up >> add user");
        log.info("RequestSignUpUserDto > " +requestSignUpUserDto);

        userService.signUpUser(requestSignUpUserDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/auth/sign-in")
    public ResponseEntity<HttpStatus> signInUser() {
        log.info("[POST] /api/auth/sign-in >> sign in");



        return new ResponseEntity<>(HttpStatus.OK);
    }

}

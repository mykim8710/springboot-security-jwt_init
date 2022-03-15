package com.example.jwtdemo.user.contoller;

import com.example.jwtdemo.user.dto.request.RequestUserJoinDto;
import com.example.jwtdemo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/token")
    public String token() {
        log.info("[POST] /token");

        return "token ok";
    }

    @PostMapping("/join")
    public ResponseEntity<Long> joinUser(@Validated  @RequestBody RequestUserJoinDto requestUserJoinDto) {
        log.info("[POST] /join => join new User");
        log.info("RequestUserJoinDto > " +requestUserJoinDto);

        return new ResponseEntity<>(userService.joinUser(requestUserJoinDto), HttpStatus.OK);
    }
}

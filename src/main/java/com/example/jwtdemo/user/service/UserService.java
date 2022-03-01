package com.example.jwtdemo.user.service;

import com.example.jwtdemo.user.dto.RequestSignUpUserDto;
import com.example.jwtdemo.user.model.User;
import com.example.jwtdemo.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUpUser(RequestSignUpUserDto requestSignUpUserDto) {
        User user = User.builder()
                            .username(requestSignUpUserDto.getUsername())
                            .password(passwordEncoder.encode(requestSignUpUserDto.getPassword()))
                            .role(requestSignUpUserDto.getRole())
                                .build();

        userMapper.saveUser(user);

        return user.getId();
    }

}

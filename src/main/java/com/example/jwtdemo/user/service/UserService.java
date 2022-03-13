package com.example.jwtdemo.user.service;

import com.example.jwtdemo.user.domain.User;
import com.example.jwtdemo.user.domain.UserAuthority;
import com.example.jwtdemo.user.dto.request.RequestUserJoinDto;
import com.example.jwtdemo.user.exception.ExistAccountException;
import com.example.jwtdemo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /* join New user */
    @Transactional
    public Long joinUser(RequestUserJoinDto dto) {
        // Check Duplication username
        checkDuplicateUsername(dto);

        // insert User
        User insertUserModel = User.builder()
                                        .username(dto.getUsername())
                                        .password(passwordEncoder.encode(dto.getPassword()))
                                        .build();

        userRepository.insertUser(insertUserModel);

        final Long userId = insertUserModel.getId();
        log.info("user id > " +userId);

        // insert UserAuthority
        List<Integer> authorities = dto.getAuthorities();
        UserAuthority insertUserAuthorityModel = UserAuthority.builder()
                                                                .userId(userId)
                                                                .build();

        authorities.forEach(authority -> {
            insertUserAuthorityModel.updateUserAuthority(authority);
            userRepository.insertUserAuthority(insertUserAuthorityModel);
        });

        return userId;
    }

    private void checkDuplicateUsername(RequestUserJoinDto dto) {
        userRepository.findUserByUsername(dto.getUsername())
                .ifPresent(user -> {
                    throw new ExistAccountException();
                });
    }


}

package com.example.jwtdemo.config.security;

import com.example.jwtdemo.user.model.User;
import com.example.jwtdemo.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
    }
}

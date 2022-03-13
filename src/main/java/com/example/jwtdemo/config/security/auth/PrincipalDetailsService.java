package com.example.jwtdemo.config.security.auth;

import com.example.jwtdemo.user.domain.User;
import com.example.jwtdemo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// localhost:9092/login => form login disable했으므로 동작을 하지않음
@RequiredArgsConstructor
@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Do it");

        User userDomain = userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return PrincipalDetail.builder()
                                .user(userDomain)
                                .build();
    }
}

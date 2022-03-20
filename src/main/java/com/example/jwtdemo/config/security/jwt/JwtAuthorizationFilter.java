package com.example.jwtdemo.config.security.jwt;

// 시큐리티가 filter를 가지고 있는 그 중 BasicAuthenticationFilter가 있음
// 권한 및 인증이 필요한특정 주소를 요청했을때, 위 필터를 무조건 타게 되어있음
// 만약에 권한 및 인증이 필요한 주소가 아니라면 이 필터로 안탐

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwtdemo.config.security.auth.PrincipalDetails;
import com.example.jwtdemo.user.domain.User;
import com.example.jwtdemo.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }


    // 인증이나 권한이 필요한 api요청이 있을때 해당 필터를 타게됨
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("인증이나 권한이 필요한 주소요청이 됨");

        String jwtHeader = request.getHeader("Authorization");
        log.info("jwtHeader > " +jwtHeader);

        // header가 있는지, 정상적인지 확인
        // 문제가 있다면 doFilter
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // jwt Token을 검증해서 정상적인 사용자인지 확인
        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");

        String username = JWT.require(Algorithm.HMAC512("jwt"))
                                .build()
                                .verify(jwtToken)
                                .getClaim("username")
                                .asString();

        // 서명이 정상적으로 됨
        if(username != null) {
            log.info("username 정상");
            User userDomain = userRepository.findUserByUsername(username).get();

            PrincipalDetails principalDetails = PrincipalDetails.builder()
                                                                    .user(userDomain)
                                                                    .build();
            log.info("principalDetails > " +principalDetails);


            // jwt Token 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어 준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 security 세션에 접근하여 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}

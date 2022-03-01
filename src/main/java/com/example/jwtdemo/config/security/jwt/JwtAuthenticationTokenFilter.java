package com.example.jwtdemo.config.security.jwt;

import com.example.jwtdemo.config.security.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// http request 당 한번 실행되는 필터 정의


@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private JwtUtils jwtUtils;
    private CustomUserDetailService customUserDetailService;

    public JwtAuthenticationTokenFilter(CustomUserDetailService customUserDetailService, JwtUtils jwtUtils) {
        this.customUserDetailService = customUserDetailService;
        this.jwtUtils = jwtUtils;
    }


    /*
     * doFilterInternal () 내부에서 수행하는 작업
     * – Authorization 헤더에서 JWT 가져 오기 (Bearer 접두사 제거)
     * – 요청에 JWT가있는 경우 유효성을 검사하고 여기에서 사용자 이름을 구문 분석합니다.
     * – 사용자 이름에서 UserDetails를 가져와 인증 개체를 만듭니다.
     * – setAuthentication (authentication) 메서드를 사용하여 SecurityContext에서 현재 UserDetails를 설정합니다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }


}

package com.example.jwtdemo.config.security.jwt;

import com.example.jwtdemo.config.security.auth.PrincipalDetail;
import com.example.jwtdemo.user.dto.request.RequestUserSignInDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 스프링 시큐리티의 기존 UsernamePasswordAuthenticationFilter가 있음
// /login 요청해서 username, password를 post로 전송하면 => UsernamePasswordAuthenticationFilter가 동작
// 하지만 securityConfig에서 formLogin을 disable했기 때문에 동작하지않는다
// JwtAuthenticationFilter를 다시 securityConfig에 등록해줘야 작동한다.

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // 이 메서드를 통해 로그인을 시도
    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter Sign in 시도 중");


        try {
            // username, password를 받아서
            ObjectMapper objectMapper = new ObjectMapper(); // json data parsing
            RequestUserSignInDto requestUserSignInDto = objectMapper.readValue(request.getInputStream(), RequestUserSignInDto.class);
            log.info("RequestUserSignInDto > " +requestUserSignInDto);

            // 임의의 토큰 생성 하고 로그인 시도
            UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(requestUserSignInDto.getUsername(), requestUserSignInDto.getPassword());

            // authenticationManager로 로그인 시도 PrincipalDetailService를 호출, loadUserByUsername 메서드가 호출
            // => 정상이면 authentication이 리턴
            // => DB에 있는 username과 password가 일치한다.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetail principalDetail = (PrincipalDetail) authentication.getPrincipal();  //  => 로그인이 성공했다
            log.info("로그인 성공, principalDetail > " +principalDetail);
            // authentication 객체가 session영역에 저장 후 리턴
            // 리턴의 이유는 권한관리를 spring security가 대신 해주기 때문
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유는 없음, 하지만 권한처리때문에 세션에 넣어줌

            // JWT 토큰을 만들어 준다.
            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // 1. attemptAuthentication() 실행 후 인증이 정상적으로 되었으면
    // 2. successfulAuthentication()가 실행됨 => JWT토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 response해주면 됨

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication > 인증성공");

        super.successfulAuthentication(request, response, chain, authResult);
    }
}

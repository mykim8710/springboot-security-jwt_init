package com.example.jwtdemo.config.security.jwt;

import com.example.jwtdemo.config.security.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

// JWT Token 생성 및 유효성을 검증하는 컴포넌트

//@RequiredArgsConstructor
//@Component
public class JwtTokenProvider {
//  JWT Secret Ket
//  Hash Algorithm 과 함께 사용될 Secret key
//  Secret Key는 Header, Payload와 결합되어 Hash 생성
//    @Value("${spring.jwt.secret}")
//    private String SECRET_KEY;
//    private long tokenValidMilisecond = 1000L * 60 * 60; // 1시간만 토큰 유효시간 : 1시간(3600 *10^-3 ms)
//
//    private final CustomUserDetailService customUserDetailService;
//
//    @PostConstruct
//    protected void init() {
//        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
//    }
//
//    // createToken
//    // JWT 토큰이 생성되고, 이 토큰값을 통해 유저 정보에 접근
//    // userPk = user를 식별할 수 있는 값
//    // roles = 권한
//    public String createToken(String account, Collection<? extends GrantedAuthority> roles) {
//        Claims claims = Jwts.claims().setSubject(account);
//        claims.put("roles", roles);
//
//        Date now = new Date();
//
//        return Jwts.builder()
//                .setClaims(claims)  // 데이터
//                .setIssuedAt(now)   // 토큰 발행일자
//                .setAudience(JwtAuth)
//
//
//                .setExpiration(new Date(now.getTime() + tokenValidMilisecond)) // set Expire Time
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 암호화 알고리즘, secret값 세팅
//                .compact();
//    }
}

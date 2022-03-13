package com.example.jwtdemo.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration
public class CorsConfig {

    //@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // 내 서버가 응답을 할때, json을 자바스크립트에서 처리할 수 있게 할지를 설정
        config.addAllowedOrigin("*");       // 모든 ip의 응답을 허용
        config.addAllowedHeader("*");       // 모든 Header의 응답을 허용
        config.addAllowedMethod("*");       // 모든 get, post, put, delete...요청을 허용

        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}

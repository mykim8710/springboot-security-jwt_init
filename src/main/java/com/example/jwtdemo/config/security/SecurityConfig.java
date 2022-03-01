package com.example.jwtdemo.config.security;

import com.example.jwtdemo.config.security.jwt.JwtAuthenticationTokenFilter;
import com.example.jwtdemo.config.security.jwt.JwtAuthenticationEntryPoint;
import com.example.jwtdemo.config.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/* Spring Security 설정 Config */


@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtUtils jwtUtils;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public JwtAuthenticationTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthenticationTokenFilter(customUserDetailService, jwtUtils);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //private final CustomUserDetailService customUserDetailService;

    // 실제 인증을 진행할 Provider
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailService);
//    }

    // 인증을 무시할 경로들을 설정 >> static resource 보안설정
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()	    // csrf 사용 안함( == )REST API 사용하기 때문에)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()	// JWT인증 사용하므로 세션 사용안함
            .authorizeRequests() // 다음 리퀘스트에 대한 사용권한 체크
            .antMatchers("/api/auth/**").permitAll() // 가입 및 인증 주소는 누구나 접근가능
            .anyRequest().hasRole("USER"); // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
//            .and()
//            .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//            .and()
//            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }



}

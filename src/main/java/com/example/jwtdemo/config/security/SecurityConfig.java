package com.example.jwtdemo.config.security;

import com.example.jwtdemo.config.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // Spring Security를 활성화한다는 의미의 @annotation
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //private final CorsConfig corsConfig;

    // 인증을 무시할 경로들을 설정 >> static resource 보안설정
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    // http 관련 인증 설정
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //httpSecurity.addFilter(corsConfig.corsFilter());    // 내 서버로 오는 모든 요청에 대해 해당 filter를 통과한다. -> cors(cross origin) filter

        //httpSecurity.addFilterBefore(new TokenFilterTest(), SecurityContextPersistenceFilter.class);
        // ㄴ security filter chain의 순서 파악 Test -> security filter보다 먼저 동작하고 싶은 필터를 등록하고 싶을때
        // ㄴ security filter chain이 Custom Filter보다 먼저 작동(FilterConfig)
        // ㄴ SecurityContextPersistenceFilter : 최상단
        

        // csrf, cors disable
        httpSecurity.cors().and().csrf().disable();

        // 세션을 사용하지 않기 때문에 STATELESS로 설정
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // id, pw의 Form Login(기본적인 Http 로그인 방식)을 사용하지 않을 것
        httpSecurity
            .formLogin().disable()  // form tag를 이용해 login하지 않을 것
            .httpBasic().disable();
            // http basic 방식   : headers -> Authorization : id, pw를 담아서 인증을 진행, http 방식은 id, pw를 암호화 하지 않기 때문에 노출 가능성이 있음 => https 서버사용
            // Bearer Token 방식 : headers -> Authorization : 암호화된 Token을 담아서 보내고 인증을 시도, 해당 Token은 유효시간이 존재, 유효시간 후에는 토큰이 만료
            // ㄴ 만료되지 않은 Token을 이용하여 타인이 인증이 가능하지만, 유효시간이 존재하므로 http basic 방식에 비해 안전
            // ㄴ Token : JWT 를 이용

        httpSecurity.addFilter(new JwtAuthenticationFilter(authenticationManager()));  // AuthenticationManager
        // 스프링 시큐리티의 기존 UsernamePasswordAuthenticationFilter가 있음
        // /login 요청해서 username, password를 post로 전송하면 => UsernamePasswordAuthenticationFilter가 동작
        // 하지만 securityConfig에서 formLogin을 disable했기 때문에 동작하지않는다
        // JwtAuthenticationFilter를 다시 securityConfig에 등록해줘야 작동한다.


        httpSecurity
            .authorizeRequests()
//                .antMatchers("/", "/sign-in", "/token")
//                    .anonymous()
                .antMatchers("/api/user/**")
                    .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/admin/**")
                    .access("hasRole('ROLE_ADMIN')")
                .anyRequest()
                    .permitAll();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

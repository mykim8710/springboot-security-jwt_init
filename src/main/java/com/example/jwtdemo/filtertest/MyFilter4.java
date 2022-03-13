package com.example.jwtdemo.filtertest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class MyFilter4 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        if(StringUtils.equals(req.getMethod(), "POST")) {
            log.info("POST 요청됨");
            String hearderAuth = (String)req.getHeader("Authorization");
            System.out.println(hearderAuth);
        }

        System.out.println("Filter 4");
        chain.doFilter(request, response);
    }
}

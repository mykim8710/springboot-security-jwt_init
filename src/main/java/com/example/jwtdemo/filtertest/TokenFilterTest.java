package com.example.jwtdemo.filtertest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class TokenFilterTest implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        // ex) Token : 코스
        if(StringUtils.equals(req.getMethod(), "POST")) {
            log.info("POST 요청됨");
            String hearderAuth = (String)req.getHeader("Authorization");
            System.out.println(hearderAuth);

            if(StringUtils.equals(hearderAuth, "auth")) {
                chain.doFilter(request, response);  // filter 통과
            } else {
                PrintWriter outPrintWriter = res.getWriter();
                outPrintWriter.println("No auth");
            }
        }

        System.out.println("Token Filter");
    }
}

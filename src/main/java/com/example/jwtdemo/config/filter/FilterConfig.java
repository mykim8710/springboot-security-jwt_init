package com.example.jwtdemo.config.filter;

import com.example.jwtdemo.filtertest.MyFilter1;
import com.example.jwtdemo.filtertest.MyFilter2;
import com.example.jwtdemo.filtertest.MyFilter4;
import com.example.jwtdemo.filtertest.TokenFilterTest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

//@Configuration
public class FilterConfig {
    //@Bean
    public FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);   // 낮은 번호가 필터중 가장먼저 실행됨
        return bean;
    }

    //@Bean
    public FilterRegistrationBean<MyFilter2> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(1);
        return bean;
    }

    //@Bean
    public FilterRegistrationBean<MyFilter4> filter4() {
        FilterRegistrationBean<MyFilter4> bean = new FilterRegistrationBean<>(new MyFilter4());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }

    //@Bean
    public FilterRegistrationBean<TokenFilterTest> filter5() {
        FilterRegistrationBean<TokenFilterTest> bean = new FilterRegistrationBean<>(new TokenFilterTest());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);

        return bean;
    }
}

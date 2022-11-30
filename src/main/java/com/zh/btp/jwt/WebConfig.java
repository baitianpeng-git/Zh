package com.zh.btp.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathLists= new ArrayList<>();
        //注册、登录允许访问，不进行拦截
        excludePathLists.add("/user/login");
        excludePathLists.add("/user/register");
        excludePathLists.add("/user/info");
        excludePathLists.add("/score/*");
		
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePathLists);
    }
}


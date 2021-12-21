package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        // /*不包括子目录，/**包括子目录 ,/ 只包括路径型url不包括后缀型url
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/teachers/**")
                .addPathPatterns("/parents/**")
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/teachers/login")
                .excludePathPatterns("/parents/login")
                .excludePathPatterns("/admin/login");

    }
}

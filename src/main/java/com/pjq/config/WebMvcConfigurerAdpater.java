package com.pjq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
//拦截器
public class WebMvcConfigurerAdpater extends WebMvcConfigurerAdapter {

    @Autowired
    UserNameInterceptor userNameInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(userNameInterceptor)
                .addPathPatterns("/api/course/my**")
                .addPathPatterns("/api/post/my/**")
                .addPathPatterns("/api/shoppingcart/**")
                .addPathPatterns("/user/showinformation");//匹配要过滤的路径
//                .excludePathPatterns("/user/**");
    }
}

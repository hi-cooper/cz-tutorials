package com.taiwii.aspectdemo.config;

import com.taiwii.aspectdemo.aspect.CustomAspect;
import com.taiwii.aspectdemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public UserService userService() {
        UserService<@CustomAspect(description = "This is TYPE_PARAMETER") String> userService =
                new @CustomAspect(description = "This is TYPE_USE") UserService();
        return userService;
    }
}

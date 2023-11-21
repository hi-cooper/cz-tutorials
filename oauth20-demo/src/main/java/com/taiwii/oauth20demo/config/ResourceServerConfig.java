package com.taiwii.oauth20demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
// @Order(3)   // 默认order已在ResourceServerConfiguration定义
// WebSecurityConfigurerAdapter用于保护oauth相关的endpoints，同时主要作用于用户的登录(form login,Basic auth)
// ResourceServerConfigurerAdapter用于保护oauth要开放的资源，同时主要作用于client端以及token的认证(Bearer auth)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")  // 所有API资源需要在此配置，否则请求API时将重定向至登录页面
                .and().authorizeRequests()
                .antMatchers("/oauth2callback/**").permitAll() // 不需要token验证URL：阿里飞燕请求用户信息
                .antMatchers("/**").authenticated(); // 所有API资源需要在此配置，否则请求API时将重定向至登录页面
        http.httpBasic().disable().cors().disable().csrf().disable();
    }
}
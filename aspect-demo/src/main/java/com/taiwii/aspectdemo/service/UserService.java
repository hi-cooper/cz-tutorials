package com.taiwii.aspectdemo.service;

import com.taiwii.aspectdemo.aspect.CustomAspect;

@CustomAspect(description = "This is TYPE")
public class UserService<T> {

    @CustomAspect(description = "This is FIELD")
    public String name;

    @CustomAspect(description = "This is CONSTRUCTOR")
    public UserService() {}

    @CustomAspect(description = "This is METHOD")
    public void test(@CustomAspect(description = "This is PARAMETER") String name) {
        @CustomAspect(description = "This is LOCAL_VARIABLE")
        String prefix = "Hello, ";
        System.out.println(prefix + name);
    }
}

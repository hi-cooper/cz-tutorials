package com.taiwii.aspectdemo;

import com.taiwii.aspectdemo.aspect.CustomAspect;
import com.taiwii.aspectdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService<@CustomAspect(description = "This is TYPE_PARAMETER") String> userService;

    @Test
    public void test() {
        userService.test("Tom");
    }
}

package com.taiwii.aspectjdemo.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    public void testPrint() {
        this.userService.print();
    }

    @Test
    @Order(2)
    public void testPrintThrowing() {
        this.userService.printThrowing();
    }
}

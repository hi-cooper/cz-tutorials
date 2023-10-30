package com.taiwii.aspectjdemo.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void print() {
        System.out.println("print()");
    }

    public void printThrowing() {
        System.out.println("printThrowing()");
        throw new RuntimeException("This is a RuntimeException");
    }
}

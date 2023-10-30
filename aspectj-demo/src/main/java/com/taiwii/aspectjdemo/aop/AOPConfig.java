package com.taiwii.aspectjdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Slf4j
@Aspect
@Configuration
public class AOPConfig {
    @Pointcut("execution(* com.taiwii..service..*.*(..))")
    public void pointCutMethod() {
    }

    @Before("pointCutMethod()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("doBefore");
    }

    @AfterReturning("pointCutMethod()")
    public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
        System.out.println("doAfterReturning");
    }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            System.out.println("before doAround");
            result = joinPoint.proceed();
            System.out.println("after doAround");
        } catch (Exception e) {
            System.out.println("after doAround Exception");
            throw e;
        }
        return result;
    }

    @AfterThrowing("pointCutMethod()")
    public void doAfterThrowing(JoinPoint joinPoint) throws Throwable {
        System.out.println("doAfterThrowing");
    }

    @After("pointCutMethod()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("doAfter");
    }
}

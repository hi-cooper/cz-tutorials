package com.taiwii.aspectdemo.aop;


import com.taiwii.aspectdemo.aspect.CustomAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.*;
import java.util.Arrays;

@Slf4j
@Aspect
@Configuration
public class UserServiceAOP {

    @Pointcut("execution(* com.taiwii..service..*.*(..))")
    public void pointCutMethod() {
    }

    /**
     * getDeclaredAnnotation(): 忽略父类继承
     * getAnnotation(): 包含继承
     *
     * @param joinPoint
     * @throws Throwable
     */
    @After("pointCutMethod()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        CustomAspect pkg = joinPoint.getSignature().getDeclaringType().getPackage().getAnnotation(CustomAspect.class);
        if (null != pkg) {
            System.out.println(pkg.description()); // This is PACKAGE
        }

        CustomAspect module = joinPoint.getSignature().getDeclaringType().getModule().getAnnotation(CustomAspect.class);
        if (null != module) {
            System.out.println(module.description()); // This is MODULE
        }

        CustomAspect cls = (CustomAspect) joinPoint.getSignature().getDeclaringType().getAnnotation(CustomAspect.class);
        if (null != cls) {
            System.out.println(cls.description()); // This is TYPE
        }

        Field[] fields = joinPoint.getSignature().getDeclaringType().getFields();
        Arrays.asList(fields).forEach((field) -> {
            CustomAspect item = field.getAnnotation(CustomAspect.class);
            if (null != item) {
                System.out.println(item.description()); // This is FIELD
            }
        });

        Constructor[] constructors = joinPoint.getSignature().getDeclaringType().getConstructors();
        Arrays.asList(constructors).forEach((constructor) -> {
            CustomAspect item = (CustomAspect) constructor.getAnnotation(CustomAspect.class);
            if (null != item) {
                System.out.println(item.description()); // This is CONSTRUCTOR
            }
        });

        CustomAspect method = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(CustomAspect.class);
        if (null != method) {
            System.out.println(method.description()); // This is METHOD
        }

        Parameter[] params = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Arrays.asList(params).forEach((param) -> {
            CustomAspect item = param.getAnnotation(CustomAspect.class);
            if (null != item) {
                System.out.println(item.description()); // This is PARAMETER
            }
        });
    }
}

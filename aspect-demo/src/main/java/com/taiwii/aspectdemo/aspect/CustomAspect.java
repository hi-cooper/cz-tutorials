package com.taiwii.aspectdemo.aspect;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE,
        ElementType.MODULE,
        ElementType.TYPE,
        ElementType.FIELD,
        ElementType.CONSTRUCTOR,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE,
        ElementType.ANNOTATION_TYPE,
        ElementType.TYPE_PARAMETER,
        ElementType.TYPE_USE,
        ElementType.RECORD_COMPONENT})
@Inherited
@Documented
public @interface CustomAspect {
    /** 描述 */
    String description();
}
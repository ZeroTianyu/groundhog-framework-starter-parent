package com.groundhog.base.annotation;

import java.lang.annotation.*;

/**
 * @Description: 返回结果注解
 * @Author Created by HOT SUN on 2020/9/13 .
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Documented
public @interface ResponseResult {
}

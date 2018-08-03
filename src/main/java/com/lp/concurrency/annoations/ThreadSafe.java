package com.lp.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * mark thread safe class
 */
//注解目标
@Target(ElementType.TYPE)
//注解使用作用阈
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe
{
    String value() default  "";
}

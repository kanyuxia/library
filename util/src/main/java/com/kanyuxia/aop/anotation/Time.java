package com.kanyuxia.aop.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Time 注解，代表使用AOP打印当前方法的执行时间
 * @author KanYuXia
 * @date 2018/1/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Time {

}

package com.kanyuxia.aop.aspect;

import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TimeAspect: 代表使用AOP打印当前方法的执行时间
 * 1. 这里使用到了AspectJ来实现AOP，当然在Spring中可以直接在类上加@Conponet注解后Spring会自动实现AOP。
 * 2. 如果不使用Spring，则需要使用Idea+AspectJ来实现AOP，具体可见：http://blog.csdn.net/xqj198404/article/details/77651768
 * 但是该方法不是能支持Lombok，具体可见：https://github.com/rzwitserloot/lombok/issues/995
 *
 * @author KanYuXia
 * @date 2018/1/9
 */
@Aspect
public class TimeAspect {

  private static Logger log = LoggerFactory.getLogger(TimeAspect.class);

  @Pointcut("@annotation(com.kanyuxia.aop.anotation.Time)")
  public void timePointCut() {

  }

  @Around("timePointCut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    long beginTime = System.currentTimeMillis();
    Object returnValue = point.proceed();
    long executeTime = System.currentTimeMillis() - beginTime;
    executeResult(point, executeTime);
    return returnValue;
  }

  private void executeResult(ProceedingJoinPoint point, long executeTime) {
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    log.debug("{} execute time: {}", method.getName(), executeTime);
  }
}

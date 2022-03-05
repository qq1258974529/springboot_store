package com.cy.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {
    @Around("execution(* com.cy.store.service.impl.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        Long end = System.currentTimeMillis();
        System.out.println("take time:" + (end - start) +"ms");
        return result;
    }
}

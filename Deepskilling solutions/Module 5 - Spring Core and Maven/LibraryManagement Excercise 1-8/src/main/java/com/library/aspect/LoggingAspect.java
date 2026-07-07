package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.*(..))")
    public void beforeMethod() {
        System.out.println("===== Before Method Execution =====");
    }

    @After("execution(* com.library.service.BookService.*(..))")
    public void afterMethod() {
        System.out.println("===== After Method Execution =====");
    }

    @Around("execution(* com.library.service.BookService.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
        return result;
    }
}

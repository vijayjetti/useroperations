package com.vijay.learning.useroperations.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.vijay.learning.useroperations.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("execution(* com.vijay.learning.useroperations.controller.*.*(..))")
    private void forControllerPackage() {}

    @Before("forServicePackage() || forControllerPackage()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        log.debug("=====>> in @Before: calling method: {}", method);
        
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.debug("=====>> argument: {}", arg);
        }
    }
}
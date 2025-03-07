package com.project.Teacher_Service.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER= LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.project.Teacher_Service.service.*.*(..))")
    public void logMethodCallBefore(JoinPoint joinPoint){
        LOGGER.info("Before Method called "+joinPoint.getSignature().getName());
    }

    @After("execution(* com.project.Teacher_Service.service.*.*(..))")
    public void logMethodCallAfter(JoinPoint joinPoint){
        LOGGER.info("After Method called "+joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.project.Teacher_Service.service.*.*(..))")
    public void logMethodCallAfterReturning(JoinPoint joinPoint){
        LOGGER.info("After Returning Method called "+joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(* com.project.Teacher_Service.service.*.*(..))")
    public void logMethodCallAfterThrowing(JoinPoint joinPoint){
        LOGGER.info("After Throwing Method called "+joinPoint.getSignature().getName());
    }
}

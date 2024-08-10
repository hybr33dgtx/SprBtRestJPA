package com.marc.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

    /*@Before(value = "execution(* com.marc.springboot.controller.*.*(..))")
    public void beforeAdvice (JoinPoint joinPoint) {

        logger.info(joinPoint.toLongString());
        logger.info("Inside Before Advice");
    }*/

    @Before(value = "execution(* com.marc.springboot.controller.*.*(..)) and args(object)")
    public void beforeAdvice (JoinPoint joinPoint, Object object) {

        logger.info("Entering "+joinPoint.getSignature().toString());
        logger.info("Param dump : "+object.toString());
    }

    @AfterReturning(value = "execution(* com.marc.springboot.controller.*.*(..)) and args(object)", returning = "returnObj")
    public void afterAdvice (JoinPoint joinPoint, Object object, Object returnObj) {

        logger.info("Param dump(after) : "+object.toString());
        logger.info("Exiting"+joinPoint.getSignature().toString());
        logger.info("Return Dump : "+returnObj.toString());
    }
}
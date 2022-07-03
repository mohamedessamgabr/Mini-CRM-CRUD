package com.essam.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspectCRM {

    // add a logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // pointcut declaration

    // for Controller package
    @Pointcut("execution(* com.essam.springdemo.controller.*.*(..))")
    public void forControllerPackage() {}


    // for service package
    @Pointcut("execution(* com.essam.springdemo.service.*.*(..))")
    public void forServicePackage() {}


    // for DAO package
    @Pointcut("execution(* com.essam.springdemo.dao.*.*(..))")
    public void forDaoPackage() {}



    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
    public void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        // Display the method we are calling
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("----------------->> in @Before calling the method : " + method);
        // Display the arguments of the method

        Object[] args = theJoinPoint.getArgs();

        for (Object arg : args) {
            myLogger.info("------------->>> The Argument : " + arg);
        }
    }

    // Add After Returning
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object result) {
        String method = theJoinPoint.getSignature().toShortString();
        myLogger.info("----------------->> in @AfterReturning calling the method : " + method);

        if (result != null) {
            System.out.println("The Result: " + result);
        }

    }

}

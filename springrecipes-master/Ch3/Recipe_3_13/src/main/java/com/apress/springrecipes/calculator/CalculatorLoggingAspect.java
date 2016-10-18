package com.apress.springrecipes.calculator;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorLoggingAspect {

    private Log log = LogFactory.getLog(this.getClass());

    @Before("execution(* *.*(..))")
    public void logJoinPoint(JoinPoint joinPoint) {
        log.info("Join point kind : "
                + joinPoint.getKind());
        log.info("Signature declaring type : "
                + joinPoint.getSignature().getDeclaringTypeName());
        log.info("Signature name : "
                + joinPoint.getSignature().getName());
        log.info("Arguments : "
                + Arrays.toString(joinPoint.getArgs()));
        log.info("Target class : "
                + joinPoint.getTarget().getClass().getName());
        log.info("This class : "
                + joinPoint.getThis().getClass().getName());
    }

}


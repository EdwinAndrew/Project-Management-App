package com.example.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@Aspect
public class ApplicationLoggerAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.example.pma.controllers..*)")
    public void definePackagePointcuts() {
        // empty method to name the location specified in the pointcut.
    }

    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp) {
        log.debug("\n \n \n");
        log.debug("********** Before Method Execution ********** \n {}.{} () with arguments[s] = {} ",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));
        log.debug("--------------------------------------");

        Object o = null;
        try{
            o = jp.proceed();
        } catch (Throwable e){
            e.printStackTrace();
        }

        log.debug("\n \n \n");
        log.debug("********** After Method Execution ********** \n {}.{} () with arguments[s] = {} ",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(),
                Arrays.toString(jp.getArgs()));
        log.debug("--------------------------------------");
        return o;
    }

}

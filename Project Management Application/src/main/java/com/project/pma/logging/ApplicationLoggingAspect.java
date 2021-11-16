package com.project.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class ApplicationLoggingAspect {
    //private final Logger log= LoggerFactory.getLogger(this.getClass());

//    @Pointcut("within(com.project.pma.Controllers..*)")
//    public void definePackagePointcuts(){
//
//    }
//
//    @After("definePackagePointcuts()")
//    public void log(){
//        log.debug(" ---------------AFFFFFTTTTTEEERRRRRRR method execution--------------- ");
//    }
//
//    @Before("definePackagePointcuts()")
//    public void logBefore(JoinPoint jp){
//        log.debug(" \n \n \n");
//        log.debug(" **************** Before Method Execution  ************* \n {}.{} () with arguments[s] = {}",jp.getSignature().getDeclaringTypeName(),
//                jp.getSignature().getDeclaringTypeName(),jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//        log.debug("--------++++++++==============___________=+++++++++++=========--------------------");
//    }

//    @Around("definePackagePointcuts()")
//    public Object logAround(ProceedingJoinPoint jp){
//        log.debug(" \n \n \n");
//        log.debug(" **************** Around Before Method Execution  ************* \n {}.{} () with arguments[s] = {}",jp.getSignature().getDeclaringTypeName(),
//                jp.getSignature().getDeclaringTypeName(),jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//        log.debug("--------++++++++==============___________=+++++++++++=========--------------------");
//
//
//        Object o=null;
//        try {
//            o=jp.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//
//
//        log.debug(" \n \n \n");
//        log.debug(" **************** Around After Method Execution  ************* \n {}.{} () with arguments[s] = {}",jp.getSignature().getDeclaringTypeName(),
//                jp.getSignature().getDeclaringTypeName(),jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//        log.debug("--------++++++++==============___________=+++++++++++=========--------------------");
//
//
//        return o;
//    }
}

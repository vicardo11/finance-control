package it.sosinski.financecontrol.logging;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class MethodInfoLogger {

    @Around("@annotation(LogInfo)")
    @SneakyThrows
    public Object logResult(ProceedingJoinPoint joinPoint) {
        var className = getClassName(joinPoint);
        var logger = getLogger(className);
        var methodName = getMethodName(joinPoint);
        var args = getArgs(joinPoint);

        logger.info(methodName + "(" + Arrays.toString(args) + ")");
        var result = joinPoint.proceed();
        logger.info(methodName + "() = " + result);

        return result;
    }

    private String getClassName(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName();
    }

    private Logger getLogger(String className) {
        return LoggerFactory.getLogger(className);
    }

    private Method getMethod(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
        return methodSignature.getMethod();
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        return getMethod(joinPoint).getName();
    }

    private Object[] getArgs(ProceedingJoinPoint joinPoint) {
        return joinPoint.getArgs();
    }
}

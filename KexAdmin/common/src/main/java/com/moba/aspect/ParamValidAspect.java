package com.moba.aspect;

import com.alibaba.fastjson.JSON;
import com.moba.annotation.ParamValid;
import com.moba.domain.Result;
import com.moba.emums.CodeType;
import com.moba.utils.ResultUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Aspect
@Component
public class ParamValidAspect {

    private static final Logger log = LoggerFactory.getLogger(ParamValidAspect.class);


    @Pointcut("execution(public * com.moba.controller..*(..))")
    public void validate() {
    }

    @Around(value = "validate()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            if (o instanceof BindingResult) {
                BindingResult result = (BindingResult) o;
                if (result.hasErrors()) {
                    return ResultUtils.error(CodeType.CODE_TYPE_101.getIndex(), result.getFieldErrors().get(0).getDefaultMessage());
                }
            }
        }
        return joinPoint.proceed();
    }
}
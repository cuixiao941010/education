package com.cx.edu.config;

import com.alibaba.fastjson.JSON;
import com.cx.edu.entity.log.Log;
import com.cx.edu.jwt.UserContext;
import com.cx.edu.log.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation( com.cx.edu.config.MyLog)")
    public void logPointCut() {

    }

    @AfterReturning(pointcut="logPointCut()",returning="joinPoint")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args);
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IPUtils.getIP(request);
        Log log = new Log();
        log.setCreateAt(LocalDateTime.now());
        log.setMethod(className + "-" + methodName);
        log.setOperation(myLog.value());
        log.setParams(params);
        log.setUserName(UserContext.getCurrentUser().getUserName());
        log.setIp(ip);
        logService.saveLog(log);

    }

}

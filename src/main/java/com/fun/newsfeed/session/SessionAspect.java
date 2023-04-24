package com.fun.newsfeed.session;

import com.fun.newsfeed.exception.NotValidSessionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SessionAspect {

  @Around("@annotation(SessionRequired)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    if (SessionHolder.getSession() == null || SessionHolder.getSession().getUsername() == null) {
      throw new NotValidSessionException("No valid session, login first");
    }
    return joinPoint.proceed();
  }
}

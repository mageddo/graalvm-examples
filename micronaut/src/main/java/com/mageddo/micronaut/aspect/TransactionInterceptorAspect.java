package com.mageddo.micronaut.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TransactionInterceptorAspect {

	@Around("execution(* io.micronaut.spring.tx.annotation.TransactionInterceptor.intercept(..))")
	public Object around(ProceedingJoinPoint pr) throws Throwable {
		return pr.proceed();
	}

}

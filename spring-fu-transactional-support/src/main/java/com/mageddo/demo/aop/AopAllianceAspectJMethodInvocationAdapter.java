package com.mageddo.demo.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class AopAllianceAspectJMethodInvocationAdapter implements MethodInvocation {

	private final ProceedingJoinPoint joinPoint;

	public AopAllianceAspectJMethodInvocationAdapter(ProceedingJoinPoint joinPoint) {
		this.joinPoint = joinPoint;
	}

	@Override
	public Method getMethod() {
		return ((MethodSignature)joinPoint.getSignature()).getMethod();
	}

	@Override
	public Object[] getArguments() {
		return joinPoint.getArgs();
	}

	@Override
	public Object proceed() throws Throwable {
		return joinPoint.proceed();
	}

	@Override
	public Object getThis() {
		return null;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return null;
	}
}

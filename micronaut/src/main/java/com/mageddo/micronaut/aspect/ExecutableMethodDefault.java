package com.mageddo.micronaut.aspect;

import io.micronaut.core.annotation.AnnotationMetadata;
import io.micronaut.core.type.Argument;
import io.micronaut.core.type.ReturnType;
import io.micronaut.inject.ExecutableMethod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class ExecutableMethodDefault implements ExecutableMethod {

	private final ProceedingJoinPoint joinPoint;

	public ExecutableMethodDefault(ProceedingJoinPoint joinPoint) {
		this.joinPoint = joinPoint;
	}

//	@Override
//	public AnnotationMetadata getAnnotationMetadata() {
//		return new MethodAnnotationMetadata(joinPoint);
//	}

	@Override
	public Method getTargetMethod() {
		return ((MethodSignature)joinPoint.getSignature()).getMethod();
	}

	@Override
	public ReturnType getReturnType() {
		return ReturnType.of(getTargetMethod().getReturnType());
	}

	@Override
	public Class getDeclaringType() {
		return joinPoint.getSignature().getDeclaringType();
	}

	@Override
	public String getMethodName() {
		return getTargetMethod().getName();
	}

	@Override
	public Argument[] getArguments() {
		final Argument[] args = new Argument[joinPoint.getArgs().length];
		for (int i = 0; i < args.length; i++) {
			args[i] = Argument.of(joinPoint.getArgs()[i].getClass());
		}
		return args;
	}

	@Override
	public Object invoke(Object instance, Object... arguments) {
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

package com.mageddo.micronaut.aspect;

import io.micronaut.aop.Interceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.annotation.AnnotationMetadata;
import io.micronaut.core.convert.value.MutableConvertibleValues;
import io.micronaut.core.type.Argument;
import io.micronaut.core.type.MutableArgumentValue;
import io.micronaut.core.type.ReturnType;
import io.micronaut.inject.ExecutableMethod;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.util.Map;

public class MethodInvocationContextDefault implements MethodInvocationContext {

	private final ProceedingJoinPoint jointPoint;
	private ExecutableMethodDefault executableMethod;

	public MethodInvocationContextDefault(ProceedingJoinPoint jointPoint) {
		this.jointPoint = jointPoint;
		this.executableMethod = new ExecutableMethodDefault(jointPoint);

	}

	@Override
	public AnnotationMetadata getAnnotationMetadata() {
		return getExecutableMethod().getAnnotationMetadata();
	}

	@Nonnull
	@Override
	public ExecutableMethod getExecutableMethod() {
		return executableMethod;
	}

	@Override
	public Map<String, MutableArgumentValue<?>> getParameters() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getTarget() {
		return jointPoint.getTarget();
	}

	@Override
	public Object proceed() throws RuntimeException {
		try {
			return jointPoint.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object proceed(Interceptor from) throws RuntimeException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Method getTargetMethod() {
		return getExecutableMethod().getTargetMethod();
	}

	@Override
	public ReturnType getReturnType() {
		return getExecutableMethod().getReturnType();
	}

	@Override
	public Class getDeclaringType() {
		return getExecutableMethod().getDeclaringType();
	}

	@Override
	public String getMethodName() {
		return getExecutableMethod().getMethodName();
	}

	@Nonnull
	@Override
	public MutableConvertibleValues<Object> getAttributes() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Argument[] getArguments() {
		return getExecutableMethod().getArguments();
	}

	@Override
	public Object invoke(Object instance, Object... arguments) {
		try {
			return this.jointPoint.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}

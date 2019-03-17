package com.mageddo.micronaut.aspect;

import io.micronaut.core.annotation.AnnotationMetadata;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MethodAnnotationMetadata implements AnnotationMetadata {

	private final ProceedingJoinPoint joinPoint;
	private final Annotation[] annotations;

	public MethodAnnotationMetadata(ProceedingJoinPoint joinPoint) {
		this.joinPoint = joinPoint;
		this.annotations = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotations();
	}

	@Override
	public boolean hasAnnotation(@Nullable String annotation) {
		return getAnnotationNames().contains(annotation);
	}

//	@Override
//	public @Nonnull <T extends Annotation> Optional<AnnotationValue<T>> findAnnotation(@Nonnull String annotation) {
//		ArgumentUtils.requireNonNull("annotation", annotation);
//		return Arrays
//			.stream(annotations)
//			.filter(it -> it.getClass().getSimpleName().equals(annotation))
//			.map(it -> toAnnotationValue(it))
//			.findFirst();
//	}


	@Nonnull
	@Override
	public Set<String> getAnnotationNames() {
		return Arrays.stream(annotations)
			.map(Annotation::toString)
			.collect(Collectors.toSet());
	}
}

package com.mageddo.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TransactionalAspectProcessor {

	private final Logger LOG = LoggerFactory.getLogger(TransactionalAspectProcessor.class);

	@Around("@annotation(org.springframework.transaction.annotation.Transactional) && execution( * *..*(..) )")
	public Object transactionalMethods(ProceedingJoinPoint point) throws Throwable {
		LOG.info("before " + point.getKind());
		Object r = point.proceed();
		LOG.info("after");
		return r;
	}
}

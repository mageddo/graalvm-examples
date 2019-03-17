package com.mageddo.micronaut.aspect;

import io.micronaut.spring.tx.annotation.TransactionInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.mageddo.micronaut.aspect.ApplicationContextUtils.context;

//@Aspect
public class TransactionalAspectProcessor {

	private final Logger LOG = LoggerFactory.getLogger(TransactionalAspectProcessor.class);
	private TransactionInterceptor transactionInterceptor;

	@Around("@annotation(io.micronaut.spring.tx.annotation.Transactional) && execution( * *..*(..) )")
	public Object transactionalMethods(ProceedingJoinPoint point) throws Throwable {
		setupMethodInterceptor();
		MethodSignature signature = (MethodSignature) point.getSignature();
		LOG.info("clazz={}, signature={}, method={}, methods={}", signature.getDeclaringType(), signature, signature.getMethod(), signature.getDeclaringType().getDeclaredMethods());
		LOG.info("method={}", signature.getMethod());
		Method method = signature.getMethod();
		LOG.info("annotations={}", Arrays.toString(method == null ? null : method.getAnnotations()));
		LOG.info("before m={}", point.getKind());

		MethodInvocationContextDefault methodInvocationContext = new MethodInvocationContextDefault(point);
		Object r = transactionInterceptor.intercept(methodInvocationContext);
		LOG.info("after");
		return r;
	}

	private void setupMethodInterceptor() {
		if(this.transactionInterceptor != null){
			return;
		}
		this.transactionInterceptor = context().getBean(TransactionInterceptor.class);
	}
}

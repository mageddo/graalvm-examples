package com.mageddo.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import static com.mageddo.demo.ApplicationContextProvider.context;

@Aspect
public class TransactionalAspectProcessor {

	private final Logger LOG = LoggerFactory.getLogger(TransactionalAspectProcessor.class);
	private TransactionInterceptor transactionInterceptor;

	@Around("@annotation(org.springframework.transaction.annotation.Transactional) && execution( * *..*(..) )")
	public Object transactionalMethods(ProceedingJoinPoint point) throws Throwable {
		setupMethodInterceptor();
		LOG.info("before " + point.getKind());
		Object r = transactionInterceptor.invoke(new AopAllianceAspectJMethodInvocationAdapter(point));
		LOG.info("after");
		return r;
	}

	private void setupMethodInterceptor() {
		if(this.transactionInterceptor != null){
			return;
		}
		this.transactionInterceptor = new TransactionInterceptor();
		this.transactionInterceptor.setTransactionManager(context().getBean(PlatformTransactionManager.class));
		this.transactionInterceptor.setTransactionAttributeSource(new AnnotationTransactionAttributeSource());
	}
}

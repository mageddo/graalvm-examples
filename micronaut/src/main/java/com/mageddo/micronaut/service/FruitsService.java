package com.mageddo.micronaut.service;

import com.mageddo.micronaut.dao.FruitsDAO;
import com.mageddo.micronaut.entity.FruitEntity;
import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.inject.Singleton;
import java.util.List;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class FruitsService {

	private final FruitsDAO fruitsDAO;

	@Transactional
	public List<FruitEntity> getFruits()  {
		createTrace();
		for (int i = 1; i <= 4; i++) {
			log.info("sleeping, second={}", i);
			sleep();
		}
		return fruitsDAO.getFruits();
	}

	public void createTraceNoTransactionAndRollback(){
		fruitsDAO.traceSelect();
		throw new IllegalStateException("rollback");
	}

	public int countTraces(){
		return fruitsDAO.countTraces();
	}

	@Transactional
	public void createTrace() {
		TransactionAspectSupport.currentTransactionStatus();
		fruitsDAO.traceSelect();
	}

	@Transactional
	public void createTraceAndRollback() {
		this.createTrace();
		throw new IllegalStateException("rollback");
	}

	@SneakyThrows
	private void sleep() {
		Thread.sleep(1000);
	}
}

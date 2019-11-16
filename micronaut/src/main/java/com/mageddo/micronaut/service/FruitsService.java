package com.mageddo.micronaut.service;

import com.mageddo.micronaut.dao.FruitsDAO;
import com.mageddo.micronaut.entity.FruitEntity;
import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;

import javax.inject.Singleton;
import java.util.List;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class FruitsService {

	private final FruitsDAO fruitsDAO;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<FruitEntity> getFruits()  {
		fruitsDAO.traceSelect();
		for (int i = 1; i <= 4; i++) {
			log.info("sleeping, second={}", i);
			sleep();
		}
		return fruitsDAO.getFruits();
	}

	@SneakyThrows
	private void sleep() {
		Thread.sleep(1000);
	}
}

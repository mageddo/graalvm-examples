package com.mageddo.micronaut.service;

import com.mageddo.micronaut.dao.FruitsDAO;
import com.mageddo.micronaut.entity.FruitEntity;
import io.micronaut.spring.tx.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class FruitsService {

	private final FruitsDAO fruitsDAO;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public FruitsService(FruitsDAO fruitsDAO) {
		this.fruitsDAO = fruitsDAO;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<FruitEntity> getFruits()  {
		fruitsDAO.traceSelect();
		for (int i = 1; i <= 4; i++) {
			logger.info("sleeping, second={}", i);
			sleep();
		}
		return fruitsDAO.getFruits();
	}

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}

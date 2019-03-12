package com.mageddo.demo.service;

import com.mageddo.demo.dao.FruitsDAO;
import com.mageddo.demo.entity.FruitEntity;
import com.mageddo.rawstringliterals.Rsl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Rsl
public class FruitsService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final FruitsDAO fruitsDAO;

	public FruitsService(FruitsDAO fruitsDAO) {
		this.fruitsDAO = fruitsDAO;
	}

	@Transactional
	public List<FruitEntity> getFruits()  {
		fruitsDAO.traceSelect();
		for (int i = 1; i <= 10; i++) {
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

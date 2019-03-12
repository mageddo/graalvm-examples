package com.mageddo.demo.service;

import com.mageddo.demo.dao.FruitsDAO;
import com.mageddo.demo.entity.FruitEntity;
import com.mageddo.rawstringliterals.Rsl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Rsl
public class FruitsService {

	private final FruitsDAO fruitsDAO;

	public FruitsService(FruitsDAO fruitsDAO) {
		this.fruitsDAO = fruitsDAO;
	}

	@Transactional
	public List<FruitEntity> getFruits()  {
		fruitsDAO.traceSelect();
		return fruitsDAO.getFruits();
	}
}

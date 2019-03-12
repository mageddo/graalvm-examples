package com.mageddo.micronaut.dao;


import com.mageddo.micronaut.entity.FruitEntity;

import java.util.List;

public interface FruitsDAO {
	void traceSelect();
	List<FruitEntity> getFruits();
}

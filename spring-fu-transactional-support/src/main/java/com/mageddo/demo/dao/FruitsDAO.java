package com.mageddo.demo.dao;

import com.mageddo.demo.entity.FruitEntity;

import java.util.List;

public interface FruitsDAO {
	void traceSelect();
	List<FruitEntity> getFruits();
}

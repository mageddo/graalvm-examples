package com.mageddo.micronaut.entity;

import com.mageddo.graal.reflection.configuration.RuntimeReflection;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

public class FruitEntity {

	private String name;

	public static RowMapper<FruitEntity> mapper() {
		return (rs, rowNum) -> new FruitEntity()
			.setName(rs.getString("NAM_FRUIT"))
			;
	}

	public String getName() {
		return name;
	}

	public FruitEntity setName(String name) {
		this.name = name;
		return this;
	}
}

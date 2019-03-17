package com.mageddo.micronaut.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mageddo.micronaut.service.FruitsService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import javax.inject.Inject;

@Controller("/fruits")
public class FruitsController {

	private final ObjectMapper objectMapper;
	private final FruitsService fruitsService;

	@Inject
	public FruitsController(ObjectMapper objectMapper, FruitsService fruitsService) {
		this.objectMapper = objectMapper;
		this.fruitsService = fruitsService;
	}

	@Get()
	@Produces(MediaType.TEXT_PLAIN)
	public String index() throws JsonProcessingException {
		return objectMapper.writeValueAsString(fruitsService.getFruits());
	}

}

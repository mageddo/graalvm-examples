package com.mageddo.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mageddo.demo.service.FruitsService;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class FruitsController {

	private final FruitsService fruitsService;
	private final ObjectMapper objectMapper;

	public FruitsController(FruitsService fruitsService, ObjectMapper objectMapper) {
		this.fruitsService = fruitsService;
		this.objectMapper = objectMapper;
	}

	public void handle(RouterFunctions.Builder router) {
		router.GET("/fruits", req -> {
			try {
				final String fruitsJson = objectMapper.writeValueAsString(this.fruitsService.getFruits());
				return ok().syncBody(fruitsJson);
			} catch (Exception e){
				return badRequest().syncBody(e.getMessage());
			}
		});
	}
}

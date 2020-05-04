package com.mageddo.micronaut.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;

import static io.micronaut.core.util.CollectionUtils.mapOf;
import static io.micronaut.http.HttpResponse.ok;

@Controller("/")
public class ViewsController {

	@Get
	@View("home")
	public HttpResponse index() {
		return ok(mapOf("loggedIn", true, "username", "Elvis"));
	}

	@Get("/logout")
	ModelAndView logout() {
		return new ModelAndView<>(
			"home",  mapOf("username", "Anonymous")
		);
	}


}

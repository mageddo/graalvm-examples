package com.mageddo.graalvm.okhttp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nativeimage.Reflection;

@Reflection(declaredMethods = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contributor {

	private String login;
	private int contributions;

	public String getLogin() {
		return login;
	}

	public Contributor setLogin(String login) {
		this.login = login;
		return this;
	}

	public int getContributions() {
		return contributions;
	}

	public Contributor setContributions(int contributions) {
		this.contributions = contributions;
		return this;
	}

	@Override
	public String toString() {
		return "{ login: " + login + ", contributors: " + contributions + " }";
	}
}

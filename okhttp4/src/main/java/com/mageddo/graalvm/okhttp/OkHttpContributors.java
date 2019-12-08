package com.mageddo.graalvm.okhttp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Comparator;
import java.util.List;

public class OkHttpContributors {

	public static final ObjectMapper PARSER = new ObjectMapper()
		.enable(SerializationFeature.INDENT_OUTPUT)
	;

	public List<Contributor> findContributors() {
		try (
			Response response = new OkHttpClient()
				.newCall(
					new Request.Builder()
						.url(HttpUrl.parse("https://api.github.com/repos/square/okhttp/contributors"))
						.build()
				)
				.execute()
		) {
			List<Contributor> contributors = PARSER.readValue(
				response.body().source().inputStream(),
				new TypeReference<List<Contributor>>(){}
			);

			contributors.sort(Comparator.comparing(Contributor::getContributions).reversed());
			return contributors.subList(0, 2);
		} catch (IOException e){
			throw new UncheckedIOException(e);
		}
	}

}

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
	private final String baseURI;

	public OkHttpContributors() {
		this.baseURI = "https://api.github.com";
	}

	public List<Contributor> findContributors() {

		Request request = new Request.Builder()
			.url(
				HttpUrl
					.parse(baseURI)
					.newBuilder()
					.addPathSegments("repos/square/okhttp/contributors")
					.build()
			)
			.build();


		try (
			Response response = new OkHttpClient()
				.newCall(request)
				.execute()
		) {
			List<Contributor> contributors = PARSER.readValue(
				response.body().source().inputStream(),
				new TypeReference<List<Contributor>>(){}
			);

			// Sort list by the most contributions.
			contributors.sort(Comparator.comparing(Contributor::getContributions).reversed());

			// Output list of contributors.
			return contributors;
		} catch (IOException e){
			throw new UncheckedIOException(e);
		}
	}

}

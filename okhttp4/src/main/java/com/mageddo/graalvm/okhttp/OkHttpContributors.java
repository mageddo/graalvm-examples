package com.mageddo.graalvm.okhttp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Comparator;
import java.util.List;

public class OkHttpContributors {

	public static final ObjectMapper PARSER = new ObjectMapper()
		.enable(SerializationFeature.INDENT_OUTPUT)
	;
	private final String baseURI;

	public OkHttpContributors() {
		this.baseURI = "http://api.github.com";
	}

	public List<Contributor> findContributors() {
		System.out.println(baseURI);
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
			Response response = new OkHttpClient
				.Builder()
				.build()
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

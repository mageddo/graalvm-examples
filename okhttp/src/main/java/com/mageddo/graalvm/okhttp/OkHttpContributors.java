package com.mageddo.graalvm.okhttp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
			Response response = createClient()
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

	private OkHttpClient createClient() {
		try {
			return new OkHttpClient.Builder()
				// caso queira validar o hostname deverá copiar o cacerts para a pasta do binario e usar o seguinte argumento
				// -Djavax.net.ssl.trustStore=cacerts
				// https://github.com/oracle/graal/issues/1034#issuecomment-470366241
				.hostnameVerifier((a, b) -> true)
				.sslSocketFactory( SSLContext.getDefault().getSocketFactory(), new X509TrustManager() {
					public void checkClientTrusted(X509Certificate[] arg0, String arg1) {}
					public void checkServerTrusted(X509Certificate[] arg0, String arg1) {}
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[0];
					}
				})
				.build();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}

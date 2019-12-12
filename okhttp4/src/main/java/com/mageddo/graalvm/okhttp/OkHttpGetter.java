package com.mageddo.graalvm.okhttp;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UncheckedIOException;

public class OkHttpGetter {

	public static String get(String url) {
		final OkHttpClient client = new OkHttpClient();
		try (
			Response response = client
				.newCall(
					new Request.Builder()
						.url(HttpUrl.parse(url))
						.build()
				)
				.execute()
		) {
			final String res = response.body().string();
			response.body().close();
			return String.format("GET %s\n\n%s%n", url, res.substring(0, Math.min(res.length(), 100)));
		} catch (IOException e){
			throw new UncheckedIOException(e);
		} finally {
			client.connectionPool().evictAll();
			client.dispatcher().executorService().shutdown();
		}
	}

}

package com.sample;

import static com.sample.ReflectionClasses.getBeans;
import static org.springframework.fu.jafu.Jafu.webApplication;
import static org.springframework.fu.jafu.web.WebFluxServerDsl.server;

import org.graalvm.nativeimage.Feature;
import org.graalvm.nativeimage.RuntimeReflection;
import org.springframework.boot.web.embedded.jetty.JettyReactiveWebServerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.fu.jafu.JafuApplication;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Application implements Feature {

	static {
		ReflectionClasses.setupClasses();
	}

	public static JafuApplication app = webApplication(a -> {

		a.beans(b -> {
			for (Class<?> bean : getBeans()) {
				b.bean(bean);
			}
		});

		a.enable(server(s -> {
			s.engine(new JettyReactiveWebServerFactory());
			s.port(s.profiles().contains("test") ? 8181 : 8080);
			s.router(r -> {
				SampleHandler handler = s.ref(SampleHandler.class);
				r.GET("/", handler::hello);
				r.GET("/api", handler::json);
				r.resources("/static/**", new ClassPathResource("static/"));

			});
			s.codecs(c -> {
//				c.string(true);
				c.resource();
				c.string();
//				c.jackson();
			});
		}));
	});

	public static void main (String[] args) {
		app.run(args);
	}
}

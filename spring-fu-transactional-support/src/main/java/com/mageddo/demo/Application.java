package com.mageddo.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mageddo.demo.controller.FruitsController;
import com.zaxxer.hikari.HikariDataSource;
import org.graalvm.nativeimage.Feature;
import org.springframework.boot.web.embedded.jetty.JettyReactiveWebServerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.fu.jafu.JafuApplication;

import java.util.Objects;

import static com.mageddo.demo.ReflectionClasses.getBeans;
import static org.springframework.fu.jafu.Jafu.webApplication;
import static org.springframework.fu.jafu.web.WebFluxServerDsl.server;

public class Application implements Feature {

	static {
		ReflectionClasses.setupClasses();
	}

	public static JafuApplication app = webApplication(a -> {
		a.beans(b -> {
			for (Class<?> bean : getBeans()) {
				b.bean(bean);
			}
			b.bean(HikariDataSource.class, () -> {
				Environment props = b.ref(Environment.class);
				final HikariDataSource ds = new HikariDataSource();
				ds.setJdbcUrl(props.getProperty("spring.datasource.url"));
				ds.setUsername(props.getProperty("spring.datasource.username"));
				ds.setPassword(props.getProperty("spring.datasource.password"));
				ds.setConnectionTestQuery(props.getProperty("spring.datasource.hikari.connectionTestQuery"));
				ds.setAutoCommit(Objects.equals(props.getProperty("spring.datasource.defaultAutoCommit"), "true"));
				ds.setTransactionIsolation(props.getProperty("spring.datasource.hikari.transactionIsolation"));
				ds.setMaximumPoolSize(Integer.valueOf(props.getProperty("spring.datasource.hikari.maximumPoolSize")));
				ds.setMinimumIdle(Integer.valueOf(props.getProperty("spring.datasource.hikari.minimumIdle")));
				ds.setDriverClassName(props.getProperty("spring.datasource.driverClassName"));
				return ds;
			});
		});

		a.enable(server(s -> {
			s.engine(new JettyReactiveWebServerFactory());
			s.port(s.profiles().contains("test") ? 8181 : 8080);
			s.router(r -> {
				s.ref(FruitsController.class).handle(r);
				r.resources("/static/**", new ClassPathResource("static/"));
			});
			s.codecs(c -> {
				c.resource();
				c.string();
				c.jackson();
			});
		}));
	});

	public static void main(String[] args) {
		app.run(args);
	}
}

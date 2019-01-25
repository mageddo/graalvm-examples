package com.mageddo.springbootgraalvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.Collections;

//@SpringBootApplication
public class GraalVmStarter extends FuncApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(GraalVmStarter.class);
//	}

	public static void main(String[] args) throws Exception {
		long t0 = System.currentTimeMillis();
		GraalVmStarter bean = new GraalVmStarter();
		bean.run();
		System.err.println(
			"Started HttpServer: " + (System.currentTimeMillis() - t0) + "ms");
		if (Boolean.getBoolean("demo.close")) {
			bean.close();
		}
	}

	@Override
	public void run() {
		SpringApplication application = new SpringApplication(GraalVmStarter.class) {
			@Override
			protected void load(ApplicationContext context, Object[] sources) {
				// We don't want the annotation bean definition reader
				// super.load(context, sources);
			}
		};
		application.setRegisterShutdownHook(false);
		application.setDefaultProperties(Collections.singletonMap("boot.active", "true"));
		application.addInitializers(this);
		application.setApplicationContextClass(ReactiveWebServerApplicationContext.class);
		application.run();
		System.err.println(MARKER);
	}
}


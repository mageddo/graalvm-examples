package com.mageddo.springbootgraalvm;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Dave Syer
 *
 */
public class BeanCountingApplicationListener
		implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

	private static Log logger = LogFactory.getLog(BeanCountingApplicationListener.class);
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (!event.getApplicationContext().equals(this.context)) {
			return;
		}
		ConfigurableApplicationContext context = event.getApplicationContext();
		log(context);
	}

	public void log(ConfigurableApplicationContext context) {
		int count = 0;
		String id = context.getId();
		List<String> names = new ArrayList<>();
		while (context != null) {
			count += context.getBeanDefinitionCount();
			names.addAll(Arrays.asList(context.getBeanDefinitionNames()));
			context = (ConfigurableApplicationContext) context.getParent();
		}
		logger.info("Bean count: " + id + "=" + count);
		logger.debug("Bean names: " + id + "=" + names);
		try {
			logger.info("Class count: " + id + "=" + ManagementFactory
					.getClassLoadingMXBean().getTotalLoadedClassCount());
		}
		catch (Throwable e) {
		}
	}

}

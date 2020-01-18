package com.mageddo.micronaut.kafka;

import com.mageddo.kafka.producer.MessageSender;
import com.mageddo.kafka.producer.MessageSenderImpl;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Configuration;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Scheduled;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.time.LocalDateTime;

@Factory
@Singleton
public class MicronautKafkaStarter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final MessageSender messageSender;

	public MicronautKafkaStarter(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public static void main(String[] args) {
		Micronaut.run(MicronautKafkaStarter.class);
	}

//	@Scheduled(cron = "0/5 * * * * *")
	public void ping(){
		messageSender.send(new ProducerRecord<>("ping", LocalDateTime.now().toString().getBytes()));
		logger.info("status=ping");
	}

//	@Scheduled(cron = "0/7 * * * * *")
	public void news(){
		messageSender.send(new ProducerRecord<>("news", LocalDateTime.now().toString().getBytes()));
		logger.info("status=news-sent");
	}

	@Scheduled(cron = "0/4 * * * * *")
	public void coffeeRequester(){
		messageSender.send(new ProducerRecord<>("coffee_request", LocalDateTime.now().toString().getBytes()));
		logger.info("status=coffee-requested");
	}

}

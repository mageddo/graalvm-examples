package com.mageddo.micronaut.kafka;

import io.micronaut.runtime.Micronaut;
import io.micronaut.scheduling.annotation.Scheduled;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
public class MicronautKafkaStarter {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final KafkaProducer kafkaProducer;

	public MicronautKafkaStarter(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	public static void main(String[] args) {
		Micronaut.run(MicronautKafkaStarter.class);
	}

//	@Scheduled(cron = "0/5 * * * * *")
	public void ping(){
		kafkaProducer.send(new ProducerRecord<>("ping", LocalDateTime.now().toString().getBytes()));
		logger.info("status=ping");
	}

//	@Scheduled(cron = "0/7 * * * * *")
	public void news(){
		kafkaProducer.send(new ProducerRecord<>("news", LocalDateTime.now().toString().getBytes()));
		logger.info("status=news-sent");
	}

	@Scheduled(cron = "0/4 * * * * *")
	public void coffeeRequester(){
		kafkaProducer.send(new ProducerRecord<>("coffee_request", LocalDateTime.now().toString().getBytes()));
		logger.info("status=coffee-requested");
	}
}

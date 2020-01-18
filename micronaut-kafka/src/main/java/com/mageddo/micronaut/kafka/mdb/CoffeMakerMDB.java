package com.mageddo.micronaut.kafka.mdb;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetStrategy;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.configuration.kafka.exceptions.KafkaListenerException;
import io.micronaut.configuration.kafka.exceptions.KafkaListenerExceptionHandler;
import io.micronaut.retry.annotation.Retryable;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Random;

@Singleton
@KafkaListener(groupId = "coffeeGroupId", clientId = "vanilla", offsetStrategy = OffsetStrategy.SYNC)
public class CoffeMakerMDB implements KafkaListenerExceptionHandler  {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Retryable
	@Topic("coffee_request")
	public void receive(ConsumerRecord<String, byte[]> record) {
		final var randomNumber = new Random().nextInt(5);
		logger.info(
			"status=making-coffee, randomNumber={}, key={}, partition={}, offset={}, record={}",
			randomNumber, record.key(), record.partition(), record.offset(), new String(record.value())
		);
		if(randomNumber == 1){
			throw new RuntimeException("Failed to consume: " + new String(record.value()));
		}
	}

	@Override
	public void handle(KafkaListenerException exception) {
		logger.info("status=could'nt-make-coffee, msg={}", exception.getMessage());
	}


}

package com.mageddo.micronaut.kafka.mdb;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetStrategy;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.configuration.kafka.exceptions.KafkaListenerException;
import io.micronaut.configuration.kafka.exceptions.KafkaListenerExceptionHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
@KafkaListener(groupId = "newsGroupId", clientId = "vanilla", offsetStrategy = OffsetStrategy.SYNC)
public class NewsMDB implements KafkaListenerExceptionHandler  {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Topic("news")
	public void receive(ConsumerRecord<String, byte[]> record) {
		logger.info(
			"status=ping, key={}, partition={}, offset={}, record={}",
			record.key(), record.partition(), record.offset(), new String(record.value())
		);
	}

	@Override
	public void handle(KafkaListenerException exception) {
		logger.info("status=custom-exception-handler, msg={}", exception.getMessage());
	}


}

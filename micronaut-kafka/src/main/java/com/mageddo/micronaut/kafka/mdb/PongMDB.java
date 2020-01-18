package com.mageddo.micronaut.kafka.mdb;

import com.mageddo.micronaut.kafka.KafkaProducer;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetStrategy;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.configuration.kafka.exceptions.KafkaListenerException;
import io.micronaut.configuration.kafka.exceptions.KafkaListenerExceptionHandler;
import io.micronaut.retry.annotation.Retryable;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
@KafkaListener(groupId = "pongGroupId", clientId = "vanilla", offsetStrategy = OffsetStrategy.SYNC)
public class PongMDB implements KafkaListenerExceptionHandler  {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final KafkaProducer kafkaProducer;

	public PongMDB(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@Retryable
	@Topic("ping")
	public void receive(ConsumerRecord<String, byte[]> record) {
		logger.info(
			"status=pong, key={}, partition={}, offset={}, record={}",
			record.key(), record.partition(), record.offset(), new String(record.value())
		);
		throw new RuntimeException("Failed to consume: " + new String(record.value()));
	}

	@Override
	public void handle(KafkaListenerException exception) {
		final var consumerRecord = (ConsumerRecord<String, byte[]>) exception.getConsumerRecord().get();
		logger.warn(
			"status=recovering, partition={}, offset={}, key={}, value={}",
			consumerRecord.partition(), consumerRecord.offset(),
			consumerRecord.key(), new String(consumerRecord.value()), exception
		);
		kafkaProducer.send(new ProducerRecord<>(dlqName(), consumerRecord.key(), consumerRecord.value()));
	}

	private String dlqName() {
		return "ping_dlq";
	}
}

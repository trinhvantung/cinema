package vn.trinhtung.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.handler.annotation.Header;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

	@Bean
	ConsumerFactory<String, Object> consumerUserFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_1");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, Object> factory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerUserFactory());

		return factory;
	}


//	@KafkaListener(topics = "", topicPartitions = @TopicPartition(topic = ""))
//	private void demo(@Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId) {
//		ConcurrentKafkaListenerContainerFactory<String, Object> factory =
//				new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setRecordFilterStrategy(new RecordFilterStrategy<String, Object>() {
//			@Override
//			public boolean filter(ConsumerRecord<String, Object> consumerRecord) {
//				return false;
//			}
//
//		});
//
//		DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory();
//		JsonDeserializer deserializer = new JsonDeserializer();
//		deserializer.addTrustedPackages("");
//
//
//	}

}

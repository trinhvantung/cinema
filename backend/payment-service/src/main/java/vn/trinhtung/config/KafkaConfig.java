package vn.trinhtung.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic refundTopic() {
        return new NewTopic("refund", 3, (short) 1);
    }

    @Bean
    public NewTopic PaymentResultTopic() {
        return new NewTopic("refund-for-cancelling-booking", 3, (short) 1);
    }

}

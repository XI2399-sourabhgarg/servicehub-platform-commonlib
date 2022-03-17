package com.unobank.servicehub.platform.commonlib.configuration;


import com.unobank.servicehub.platform.commonlib.configuration.properties.TopicDetails;
import com.unobank.servicehub.platform.commonlib.util.JsonSerializer;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka")
@Data
public class KafkaProducerConfig {

    @Autowired
    KafkaProperties kafkaProperties;


    private TopicDetails topicOne;

    private TopicDetails topicTwo;


    @Bean
    public Map<String, Object> producerConfigs() throws FileNotFoundException {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        props.put(ConsumerConfig.CLIENT_ID_CONFIG, kafkaProperties.getClientId());
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaProperties.getSecurity().getProtocol());
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, ResourceUtils.getFile("classpath:kafka.client.truststore.jks").getAbsolutePath());
        props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, StringUtils.EMPTY);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() throws FileNotFoundException {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() throws FileNotFoundException {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaAdmin kafkaAdmin() throws FileNotFoundException {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        /* configs.put(ConsumerConfig.CLIENT_ID_CONFIG, kafkaProperties.getClientId());*/
        configs.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, kafkaProperties.getSecurity().getProtocol());
        configs.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, ResourceUtils.getFile("classpath:kafka.client.truststore.jks").getAbsolutePath());
        configs.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, StringUtils.EMPTY);

        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topicOne() {
        return new NewTopic(topicOne.getName(), topicOne.getNumOfPartition(), (short) topicOne.getReplicationFactor());
    }


    @Bean
    public NewTopic topicTwo() {
        return new NewTopic(topicTwo.getName(), topicTwo.getNumOfPartition(), (short) topicTwo.getReplicationFactor());
    }
}

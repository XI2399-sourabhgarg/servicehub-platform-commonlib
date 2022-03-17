package com.unobank.servicehub.platform.commonlib.producer;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@Log4j2
public class KafkaProducerService<V> implements KafkaProducer<V> {

    @Autowired
    KafkaTemplate<String, V> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(String topic, String key, V value) {
        Executor exec = Executors.newSingleThreadExecutor();
        exec.execute(() -> {
            sendCallbackEvents(kafkaTemplate, topic, key, value);
        });
    }

    @Override
    public void produce(String topic, String key, List<V> values) {
        values.forEach(value -> {
            sendCallbackEvents(kafkaTemplate, topic, key, value);
        });
    }

    private void sendCallbackEvents(KafkaTemplate<String, V> kafkaTemplate, String topic, String key, V value) {
        ListenableFuture<SendResult<String, V>> future = kafkaTemplate.send(topic, key, value);
        future.addCallback(new ListenableFutureCallback<SendResult<String, V>>() {
            @Override
            public void onSuccess(SendResult<String, V> result) {
                log.info(String.format("Produced event to topic %s: key = %-10s value = %s", topic, key, value));
            }

            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
            }
        });
    }
}

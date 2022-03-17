package com.unobank.servicehub.platform.commonlib.producer;

import java.util.List;

public interface KafkaProducer<V> {

    void produce(String topic, String key, V event);

    void produce(String topic, String key, List<V> events) ;

}

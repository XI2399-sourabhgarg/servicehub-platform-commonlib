package com.unobank.servicehub.platform.commonlib.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class JsonDeSerializer<T> implements Deserializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, new TypeReference<T>() {});
        } catch (IOException e) {
            throw new SerializationException("Error deSerializing JSON message", e);
        }
    }
}

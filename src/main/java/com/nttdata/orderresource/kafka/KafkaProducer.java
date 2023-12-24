package com.nttdata.orderresource.kafka;

public interface KafkaProducer<T> {
    void sendMessage(T data);
}

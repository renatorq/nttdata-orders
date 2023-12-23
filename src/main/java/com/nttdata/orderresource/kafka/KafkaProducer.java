package com.nttdata.orderresource.kafka;

import com.nttdata.orderresource.dto.DetalleOrdenEntradaDTO;

public interface KafkaProducer<T> {
    void sendMessage(T data);
}

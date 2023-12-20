package com.nttdata.orderresource.kafka;

import com.nttdata.orderresource.model.Articulo;
import com.nttdata.orderresource.model.OrdenEntradaDetalle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {
    @Value("${spring.kafka.topic.name}")
    private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, OrdenEntradaDetalle> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrdenEntradaDetalle> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrdenEntradaDetalle data) {

        LOGGER.info(String.format("Message sent -> %s", data.toString()));

        Message<OrdenEntradaDetalle> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .build();

        kafkaTemplate.send(message);
    }

}

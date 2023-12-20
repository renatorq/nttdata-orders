package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.kafka.KafkaProducer;
import com.nttdata.orderresource.model.OrdenEntradaDetalle;
import com.nttdata.orderresource.repository.OrdenEntradaDetalleRepository;
import com.nttdata.orderresource.service.OrdenEntradaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenEntradaDetalleServiceImpl implements OrdenEntradaDetalleService {

    @Autowired
    private OrdenEntradaDetalleRepository detalleConsultaRepository;
    private KafkaProducer kafkaProducer;

    @Override
    public void guardarDetalleOrden(OrdenEntradaDetalle detalle) throws Exception {

        try {
            detalleConsultaRepository.save(detalle);
            kafkaProducer.sendMessage(detalle);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

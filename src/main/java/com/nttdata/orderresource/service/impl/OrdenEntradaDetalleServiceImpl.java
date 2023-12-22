package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.dto.DetalleOrdenEntradaDTO;
import com.nttdata.orderresource.kafka.KafkaProducer;
import com.nttdata.orderresource.model.OrdenEntradaDetalle;
import com.nttdata.orderresource.repository.OrdenEntradaDetalleRepository;
import com.nttdata.orderresource.service.OrdenEntradaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenEntradaDetalleServiceImpl implements OrdenEntradaDetalleService {

    @Autowired
    private OrdenEntradaDetalleRepository detalleConsultaRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void enviarMensaje(List<OrdenEntradaDetalle> detalle) throws Exception {

        try {

            for (OrdenEntradaDetalle d : detalle) {

                DetalleOrdenEntradaDTO detalleOrdenEntradaDTO = this.cargarDTO(d);

                kafkaProducer.sendMessage(detalleOrdenEntradaDTO);
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private DetalleOrdenEntradaDTO cargarDTO(OrdenEntradaDetalle d) {

        DetalleOrdenEntradaDTO detalleDTO = new DetalleOrdenEntradaDTO();

        detalleDTO.setIdDetalleEntrada(d.getIdDetalleEntrada());
        detalleDTO.setIdOrdenEntrada(d.getOrdenEntrada().getIdOrdenEntrada());
        detalleDTO.setCantidad(d.getCantidad());
        detalleDTO.setPrecioUnitario(d.getPrecioUnitario());
        detalleDTO.setIdArticulo(d.getArticulo().getIdArticulo());

        return detalleDTO;

    }
}

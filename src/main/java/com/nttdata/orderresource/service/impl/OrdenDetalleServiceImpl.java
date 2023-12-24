package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.dto.DetalleOrdenDTO;
import com.nttdata.orderresource.kafka.KafkaProducer;
import com.nttdata.orderresource.model.OrdenDetalle;
import com.nttdata.orderresource.repository.OrdenDetalleRepository;
import com.nttdata.orderresource.service.OrdenDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenDetalleServiceImpl implements OrdenDetalleService {

    @Autowired
    private OrdenDetalleRepository detalleConsultaRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public void enviarMensaje(List<OrdenDetalle> detalle,String tipoOrden) throws Exception {

        try {
            for (OrdenDetalle d : detalle) {
                DetalleOrdenDTO detalleOrdenDTO = this.cargarDTO(d,tipoOrden);
                kafkaProducer.sendMessage(detalleOrdenDTO);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private DetalleOrdenDTO cargarDTO(OrdenDetalle d,String tipoOrden) {
        DetalleOrdenDTO detalleDTO = new DetalleOrdenDTO();
        detalleDTO.setIdDetalle(d.getIdDetalleOrden());
        detalleDTO.setIdOrden(d.getOrden().getIdOrden());
        detalleDTO.setCantidad(d.getCantidad());
        detalleDTO.setPrecioUnitario(d.getPrecioUnitario());
        detalleDTO.setIdArticulo(d.getArticulo().getIdArticulo());
        detalleDTO.setTipoOrden(tipoOrden);
        return detalleDTO;
    }
}

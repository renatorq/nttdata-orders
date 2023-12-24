package com.nttdata.orderresource.service;

import com.nttdata.orderresource.model.OrdenDetalle;

import java.util.List;

public interface OrdenDetalleService {

    void enviarMensaje(List<OrdenDetalle> detalle,String tipoOrden) throws Exception;

}

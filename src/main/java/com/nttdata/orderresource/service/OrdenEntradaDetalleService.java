package com.nttdata.orderresource.service;

import com.nttdata.orderresource.model.OrdenEntradaDetalle;

import java.util.List;

public interface OrdenEntradaDetalleService {

    void enviarMensaje(List<OrdenEntradaDetalle> detalle) throws Exception;

}

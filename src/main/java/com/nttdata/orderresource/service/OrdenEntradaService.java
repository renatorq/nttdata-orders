package com.nttdata.orderresource.service;

import com.nttdata.orderresource.model.OrdenEntrada;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrdenEntradaService {

    void registroOrdenEntrada(OrdenEntrada oe) throws Exception;

    List<OrdenEntrada> listarOrdenEntradaxFechas(LocalDate fechaInicio, LocalDate fechaFin);

    OrdenEntrada obtenerOrdenEntrada(Integer id);

}

package com.nttdata.orderresource.service;

import com.nttdata.orderresource.model.Orden;

import java.time.LocalDate;
import java.util.List;

public interface OrdenEntradaService {

    void registroOrdenEntrada(Orden oe) throws Exception;

    List<Orden> listarOrdenEntradaxFechas(LocalDate fechaInicio, LocalDate fechaFin);

    Orden obtenerOrdenEntrada(Integer id);

}

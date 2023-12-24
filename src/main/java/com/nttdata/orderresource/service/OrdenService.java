package com.nttdata.orderresource.service;

import com.nttdata.orderresource.model.Orden;

import java.time.LocalDate;
import java.util.List;

public interface OrdenService {

    void registroOrdenEntrada(Orden oe) throws Exception;

    List<Orden> listarOrdenxFechasyTipoOrden(LocalDate fechaInicio, LocalDate fechaFin, String tipoOrden);

    Orden obtenerOrden(Integer id);

    Boolean anularOrden(Integer id) throws Exception;

}

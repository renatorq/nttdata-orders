package com.nttdata.orderresource.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleOrdenEntradaDTO {

    private Integer idDetalleEntrada;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private Integer idArticulo;

}

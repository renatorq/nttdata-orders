package com.nttdata.orderresource.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class OrdenEntradaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleEntrada;

    @ManyToOne
    private OrdenEntrada ordenEntrada;

    @ManyToOne
    private Articulo articulo;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;

}

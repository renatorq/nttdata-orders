package com.nttdata.orderresource.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(name = "idOrdenEntrada")
    @JsonIgnoreProperties
    private OrdenEntrada ordenEntrada;

    @ManyToOne
    @JoinColumn(name = "idArticulo")
    @JsonIgnoreProperties
    private Articulo articulo;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;


}

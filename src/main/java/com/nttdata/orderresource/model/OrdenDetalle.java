package com.nttdata.orderresource.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class OrdenDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleOrden;

    @ManyToOne
    @JoinColumn(name = "idOrden")
    @JsonBackReference
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "idArticulo")
    @JsonIgnoreProperties
    private Articulo articulo;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;


}

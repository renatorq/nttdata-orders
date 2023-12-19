package com.nttdata.orderresource.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class OrdenEntradaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEntrada;

    @ManyToOne
    @JoinColumn(name = "idOrdenEntrada", nullable = false, foreignKey = @ForeignKey(name = "fkOrdenEntradaDetalle"))
    private OrdenEntrada ordenEntrada;

    @ManyToOne
    @JoinColumn(name = "id_articulo", nullable = false, foreignKey = @ForeignKey(name = "fkDetalleArticulo"))
    private Articulo articulo;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "precioUnitario", nullable = false)
    private BigDecimal precioUnitario;

}

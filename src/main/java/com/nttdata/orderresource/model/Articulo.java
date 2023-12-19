package com.nttdata.orderresource.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArticulo;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

}

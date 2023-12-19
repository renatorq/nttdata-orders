package com.nttdata.orderresource.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdenEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdenEntrada;

    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false, foreignKey = @ForeignKey(name = "fkOrdenEntradaProveedor"))
    private Proveedor proveedor;

    @Column(name = "fechaentrada", nullable = false)
    private LocalDate fechaentrada;

    @Transient
    List<Articulo> listaArticulos;

}

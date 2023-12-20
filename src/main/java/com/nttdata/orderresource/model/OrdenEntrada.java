package com.nttdata.orderresource.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdenEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrdenEntrada;

    @Column(name = "fechaentrada", nullable = false)
    private LocalDate fechaentrada;

    @ManyToOne
    @JoinColumn(name = "idProveedor", nullable = false, foreignKey = @ForeignKey(name = "fkOrdenEntradaProveedor"))
    private Proveedor proveedor;

    @OneToMany(mappedBy = "ordenEntrada", cascade = CascadeType.ALL)
    private List<OrdenEntradaDetalle> detalle;

}

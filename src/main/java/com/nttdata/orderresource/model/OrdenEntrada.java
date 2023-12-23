package com.nttdata.orderresource.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaentrada", nullable = false)
    private LocalDate fechaentrada;

    @Column(name = "estado")
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    @OneToMany(mappedBy = "ordenEntrada", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<OrdenEntradaDetalle> detalle;

    @Override
    public String toString() {
        return "OrdenEntrada{" +
                "idOrdenEntrada=" + idOrdenEntrada +
                ", fechaEntrada=" + fechaentrada +
                ", proveedor=" + (proveedor != null ? proveedor.getIdProveedor() : "null") +
                /* otros campos, pero evita imprimir detalles para evitar la referencia cíclica */ +
                '}';
    }

}

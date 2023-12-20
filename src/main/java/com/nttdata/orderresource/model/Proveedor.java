package com.nttdata.orderresource.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProveedor;

    @ManyToOne
    @JoinColumn(name = "idTipoDocumento")
    private TipoDocumento tipoDocumento;

    @Column(name = "nroDocumento", length = 11)
    private String nroDocumento;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

}

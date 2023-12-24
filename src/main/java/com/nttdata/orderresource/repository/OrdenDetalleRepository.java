package com.nttdata.orderresource.repository;

import com.nttdata.orderresource.model.OrdenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenDetalleRepository extends JpaRepository<OrdenDetalle,Integer> {
}

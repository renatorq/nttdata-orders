package com.nttdata.orderresource.repository;

import com.nttdata.orderresource.model.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden,Integer> {

    List<Orden> findByFecharegistroBetween(LocalDate fechaInicio, LocalDate fechaFin);

}

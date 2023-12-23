package com.nttdata.orderresource.repository;

import com.nttdata.orderresource.model.OrdenEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrdenEntradaRepository extends JpaRepository<OrdenEntrada,Integer> {

    List<OrdenEntrada> findByFechaentradaBetween(LocalDate fechaInicio, LocalDate fechaFin);

}

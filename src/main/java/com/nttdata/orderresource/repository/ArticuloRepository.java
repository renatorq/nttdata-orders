package com.nttdata.orderresource.repository;

import com.nttdata.orderresource.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
}

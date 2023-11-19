package com.santander.products.repository;

import com.santander.products.model.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}

package com.santander.products.service;

import com.santander.products.model.entity.Producto;

import java.util.List;

public interface IProductoService {

    Producto findByProductoId(long id);

    Producto findByNombre(String nombre);

    List<Producto> findAll();

    Producto save(Producto producto);

    boolean delete(long id);

}

package com.santander.products.service;

import com.santander.products.exception.ProductNotFoundException;
import com.santander.products.exception.ProductoAlreadyExistsException;
import com.santander.products.exception.ProductoNotDeletedException;
import com.santander.products.exception.ProductoNotSavedException;
import com.santander.products.model.entity.Producto;
import com.santander.products.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ProductoService implements IProductoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoService.class);

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto findByProductoId(long id) {
        return productoRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Producto findByNombre(String nombre) {
        return productoRepository.findByNombre(nombre)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Producto> findAll() {
        return StreamSupport
                .stream(productoRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Producto save(Producto producto) {
        if (productoRepository.existsByName(producto.getNombre()))
            throw new ProductoAlreadyExistsException();

        return Optional.of(productoRepository.save(producto))
                .orElseThrow(ProductoNotSavedException::new);
    }

    @Override
    public String delete(long id) {
        productoRepository.delete(findByProductoId(id));
        if (productoRepository.existsById(id)) throw new ProductoNotDeletedException();
        return "{\"productDeleted\": true}";
    }
}

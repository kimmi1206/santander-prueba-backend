package com.santander.products.controller;

import com.santander.products.model.entity.Producto;
import com.santander.products.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping("/by-id/{productoId}")
    public ResponseEntity<Producto> getById(@PathVariable long productoId) {
        return new ResponseEntity<>(productoService.findByProductoId(productoId), HttpStatus.OK);
    }

    @GetMapping("/by-nombre")
    public ResponseEntity<Producto> getByName(@RequestParam String nombre) {
        return new ResponseEntity<>(productoService.findByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producto>> getAll() {
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Producto> save(@Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    @DeleteMapping(value = "/delete/{productoId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> delete(@PathVariable long productoId) {
        return ResponseEntity.ok(productoService.delete(productoId));
    }
}

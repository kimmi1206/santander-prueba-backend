package com.santander.products.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.products.model.entity.Producto;
import com.santander.products.repository.ProductoRepository;
import com.santander.products.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
class ProductoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoRepository productoRepository;

    @MockBean
    ProductoService productoService;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        String productoMappedToString = asJsonString(getValidProducto());
        mockMvc.perform(post("/api/productos/save")
                        .contentType("application/json")
                        .content(productoMappedToString))
                .andExpect(status().isOk());
    }

    @Test
    void whenInvalidInput_thenReturns400() throws Exception {
        String productoMappedToString = asJsonString(getInvalidProducto());
        mockMvc.perform(post("/api/productos/save", 42L)
                        .contentType("application/json")
                        .content(productoMappedToString))
                .andExpect(status().isBadRequest());
    }

    Producto getValidProducto() {
        return Producto.builder()
                .nombre("Office License " + UUID.randomUUID())
                .precio(102034050)
                .build();
    }

    Producto getInvalidProducto() {
        return new Producto(12345L, "", 12345);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

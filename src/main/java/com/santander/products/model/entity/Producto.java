package com.santander.products.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "producto_id")
    private long productoId;

    @NotBlank
    @Length(min = 3, max = 100)
    @Column(name = "nombre", unique = true, length = 100, nullable = false)
    private String nombre;

    @PositiveOrZero
    @Column(name = "precio")
    private double precio;

}

package com.examen.ms_pedidos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoRequest {

    @NotBlank(message = "El cliente es obligatorio")
    private String cliente;

    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correoCliente;

    @NotNull(message = "El productoId es obligatorio")
    private Long productoId;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombreProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precioUnitario;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}
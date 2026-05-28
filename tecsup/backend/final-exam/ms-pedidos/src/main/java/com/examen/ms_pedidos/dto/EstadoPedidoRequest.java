package com.examen.ms_pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EstadoPedidoRequest {

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}
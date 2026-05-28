package com.examen.ms_pedidos.controller;

import com.examen.ms_pedidos.dto.EstadoPedidoRequest;
import com.examen.ms_pedidos.dto.PedidoRequest;
import com.examen.ms_pedidos.entity.Pedido;
import com.examen.ms_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido crearPedido(
            @Valid @RequestBody PedidoRequest request) {

        return pedidoService.crearPedido(request);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(
            @PathVariable Long id) {

        return pedidoService.obtenerPedidoPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarPedido(
            @PathVariable Long id) {

        pedidoService.eliminarPedido(id);
    }

    @PatchMapping("/{id}/estado")
    public Pedido actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody EstadoPedidoRequest request) {

        return pedidoService.actualizarEstado(
                id,
                request.getEstado());
    }
}
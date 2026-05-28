package com.examen.ms_pedidos.service;

import com.examen.ms_pedidos.dto.PedidoRequest;
import com.examen.ms_pedidos.entity.Pedido;
import com.examen.ms_pedidos.exception.PedidoNotFoundException;
import com.examen.ms_pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public Pedido crearPedido(PedidoRequest request) {

        BigDecimal total =
                request.getPrecioUnitario()
                        .multiply(BigDecimal.valueOf(request.getCantidad()));

        Pedido pedido = Pedido.builder()
                .cliente(request.getCliente())
                .correoCliente(request.getCorreoCliente())
                .productoId(request.getProductoId())
                .nombreProducto(request.getNombreProducto())
                .cantidad(request.getCantidad())
                .precioUnitario(request.getPrecioUnitario())
                .total(total)
                .estado(request.getEstado())
                .fechaPedido(LocalDateTime.now())
                .build();

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new PedidoNotFoundException(
                                "Pedido no encontrado con ID: " + id));
    }

    public void eliminarPedido(Long id) {

        Pedido pedido = obtenerPedidoPorId(id);

        pedidoRepository.delete(pedido);
    }

    public Pedido actualizarEstado(Long id, String estado) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new PedidoNotFoundException(
                                "Pedido no encontrado con ID: " + id));

        pedido.setEstado(estado);

        return pedidoRepository.save(pedido);
    }
}
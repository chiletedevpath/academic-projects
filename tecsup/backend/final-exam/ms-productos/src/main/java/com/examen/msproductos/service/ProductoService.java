package com.examen.msproductos.service;

import com.examen.msproductos.dto.ProductoRequest;
import com.examen.msproductos.entity.Producto;
import com.examen.msproductos.exception.RecursoNoEncontradoException;
import com.examen.msproductos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductoService {

    private final ProductoRepository productoRepository;

    public Producto crearProducto(ProductoRequest request) {

        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .estado(request.getEstado())
                .fechaCreacion(LocalDateTime.now())
                .build();

        return productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {

        return productoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Producto no encontrado")
                );
    }

    public Producto actualizarProducto(Long id, ProductoRequest request) {

        Producto producto = buscarPorId(id);

        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setEstado(request.getEstado());

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {

        Producto producto = buscarPorId(id);

        productoRepository.delete(producto);

    }
}
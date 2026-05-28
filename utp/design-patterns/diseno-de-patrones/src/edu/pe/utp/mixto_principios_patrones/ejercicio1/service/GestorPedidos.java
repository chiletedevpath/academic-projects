package edu.pe.utp.mixto_principios_patrones.ejercicio1.service;

import edu.pe.utp.mixto_principios_patrones.ejercicio1.model.Pedido;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.observer.ObservadorPedido;

import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {

    // Singleton
    private static GestorPedidos instancia;

    // Datos del sistema
    private final List<Pedido> pedidos;
    private final List<ObservadorPedido> observadores;

    // Constructor privado
    private GestorPedidos() {
        pedidos = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    // Metodo para obtener la única instancia
    public static GestorPedidos getInstancia() {
        if (instancia == null) {
            instancia = new GestorPedidos();
        }
        return instancia;
    }

    // Agregar observadores
    public void agregarObservador(ObservadorPedido observador) {
        observadores.add(observador);
    }

    // Eliminar observadores
    public void eliminarObservador(ObservadorPedido observador) {
        observadores.remove(observador);
    }

    // Registrar pedido
    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
        System.out.println("Pedido registrado: " + pedido);

        // Notificar a todos los observadores
        notificarObservadores(pedido);
    }

    // Notificación interna
    private void notificarObservadores(Pedido pedido) {
        for (ObservadorPedido observador : observadores) {
            observador.actualizar(pedido);
        }
    }

    // Obtener lista de pedidos
    public List<Pedido> getPedidos() {
        return pedidos;
    }
}

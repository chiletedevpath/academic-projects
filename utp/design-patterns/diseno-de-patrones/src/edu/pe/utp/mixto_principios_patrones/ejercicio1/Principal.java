package edu.pe.utp.mixto_principios_patrones.ejercicio1;

import edu.pe.utp.mixto_principios_patrones.ejercicio1.adapter.AdaptadorWhatsApp;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.adapter.Notificador;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.adapter.ServicioWhatsAppExterno;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.model.Pedido;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.observer.ObservadorCliente;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.observer.ObservadorLog;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.observer.ObservadorPedido;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.observer.ObservadorWhatsApp;
import edu.pe.utp.mixto_principios_patrones.ejercicio1.service.GestorPedidos;

public class Principal {

    public static void main(String[] args) {

        // 1. Obtener instancia única (Singleton)
        GestorPedidos gestor = GestorPedidos.getInstancia();

        // 2. Crear observadores
        ObservadorPedido log = new ObservadorLog();
        ObservadorPedido cliente = new ObservadorCliente();

        // 3. Crear servicio externo + adapter
        ServicioWhatsAppExterno servicioExterno = new ServicioWhatsAppExterno();
        Notificador adaptador = new AdaptadorWhatsApp(servicioExterno);
        ObservadorPedido whatsapp = new ObservadorWhatsApp(adaptador);

        // 4. Registrar observadores
        gestor.agregarObservador(log);
        gestor.agregarObservador(cliente);
        gestor.agregarObservador(whatsapp);

        // 5. Crear pedidos
        Pedido pedido1 = new Pedido(1, "Adrian", 150.00);
        Pedido pedido2 = new Pedido(2, "Lucia", 220.50);

        // 6. Registrar pedidos
        gestor.registrarPedido(pedido1);
        System.out.println("--------------------------------------------------");
        gestor.registrarPedido(pedido2);
    }
}


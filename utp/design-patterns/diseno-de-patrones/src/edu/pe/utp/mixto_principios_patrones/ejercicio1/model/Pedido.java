package edu.pe.utp.mixto_principios_patrones.ejercicio1.model;

public class Pedido {

    private final int id;
    private final String cliente;
    private final double monto;

    public Pedido(int id, String cliente, double monto) {
        this.id = id;
        this.cliente = cliente;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Pedido{id=" + id + ", cliente='" + cliente + "', monto=" + monto + "}";
    }
}

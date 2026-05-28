package edu.pe.utp.principios_solid.practica_calificada.ejercicio2;

public class Pedido {

    private String tipoCliente;
    private String tipoEnvio;
    private double monto;

    private Descuento descuento;
    private Envio envio;

    public Pedido(String tipoCliente, String tipoEnvio, double monto, Descuento descuento, Envio envio) {
        this.tipoCliente = tipoCliente;
        this.tipoEnvio = tipoEnvio;
        this.monto = monto;
        this.descuento = descuento;
        this.envio = envio;
    }

    public double calcularTotal() {
        double descuentoAplicado = descuento.aplicarDescuento(monto);
        double costoEnvio = envio.calcularCostoEnvio();

        return monto - descuentoAplicado + costoEnvio;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public double getMonto() {
        return monto;
    }
}
package edu.pe.utp.patrones_diseno.estructural.adapter.ejercicio1;

public class AdaptadorVentas implements SistemaFacturacion{

    private SistemaVentasAntiguo sistemaVentasAntiguo;

    public AdaptadorVentas(SistemaVentasAntiguo sistemaVentasAntiguo) {
        this.sistemaVentasAntiguo = sistemaVentasAntiguo;
    }

    @Override
    public void procesarFactura() {
        sistemaVentasAntiguo.generarReporte();
    }
}

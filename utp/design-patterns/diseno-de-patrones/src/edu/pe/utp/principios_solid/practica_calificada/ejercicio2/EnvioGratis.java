package edu.pe.utp.principios_solid.practica_calificada.ejercicio2;

public class EnvioGratis implements Envio {

    @Override
    public double calcularCostoEnvio() {
        return 0;
    }
}
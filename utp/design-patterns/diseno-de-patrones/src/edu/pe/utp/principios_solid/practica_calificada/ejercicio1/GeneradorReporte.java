package edu.pe.utp.principios_solid.practica_calificada.ejercicio1;

import java.util.List;

// Clase encargada solo de generar el reporte
class GeneradorReporte {

    public String generarReporte(List<String> datos) {
        StringBuilder reporte = new StringBuilder();
        for (String d : datos) {
            reporte.append(d).append("\n");
        }
        return reporte.toString();
    }
}
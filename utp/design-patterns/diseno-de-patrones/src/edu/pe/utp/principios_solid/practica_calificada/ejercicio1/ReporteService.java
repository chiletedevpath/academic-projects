package edu.pe.utp.principios_solid.practica_calificada.ejercicio1;

import java.util.ArrayList;
import java.util.List;

// Clase encargada solo de gestionar los datos del reporte
class ReporteService {

    private List<String> datos = new ArrayList<>();

    public void agregarDato(String dato) {
        datos.add(dato);
    }

    public List<String> getDatos() {
        return datos;
    }
}

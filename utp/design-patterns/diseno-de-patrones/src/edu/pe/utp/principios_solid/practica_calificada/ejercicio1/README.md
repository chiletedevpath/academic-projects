# Aplicar uno de los principios SOLID con JAVA

```java
import java.util.ArrayList;
import java.util.List;

class ReporteService {

    private List<String> datos = new ArrayList<>();

    public void agregarDato(String dato) {
        datos.add(dato);
    }

    public String generarReporte() {

        StringBuilder reporte = new StringBuilder();

        for (String d : datos) {
            reporte.append(d).append("\n");
        }

        return reporte.toString();
    }

    public void guardarEnArchivo(String reporte) {

        System.out.println("Guardando en archivo...");

        // Simulación
    }

    public void guardarEnBaseDeDatos(String reporte) {

        System.out.println("Guardando en base de datos...");
    }

    public void enviarPorEmail(String reporte, String email) {

        System.out.println("Enviando reporte a: " + email);
    }

    public void imprimirReporte(String reporte) {

        System.out.println("Imprimiendo reporte...");
        System.out.println(reporte);
    }
}

public class Main {

    public static void main(String[] args) {

        ReporteService reporteService = new ReporteService();

        // Agregar datos al reporte
        reporteService.agregarDato("Venta 1: S/ 150");
        reporteService.agregarDato("Venta 2: S/ 300");
        reporteService.agregarDato("Venta 3: S/ 500");

        // Generar reporte
        String reporte = reporteService.generarReporte();

        // Guardar reporte
        reporteService.guardarEnArchivo(reporte);

        // Guardar en base de datos
        reporteService.guardarEnBaseDeDatos(reporte);

        // Enviar reporte por email
        reporteService.enviarPorEmail(reporte, "adrian@gmail.com");

        // Imprimir reporte
        reporteService.imprimirReporte(reporte);
    }
}
package edu.pe.utp.principios_solid.practica_calificada.ejercicio1;

public class Principal {

    public static void main(String[] args) {

        ReporteService reporteService = new ReporteService();
        reporteService.agregarDato("Venta 1: S/ 150");
        reporteService.agregarDato("Venta 2: S/ 200");
        reporteService.agregarDato("Venta 3: S/ 300");

        GeneradorReporte generador = new GeneradorReporte();
        String reporte = generador.generarReporte(reporteService.getDatos());

        GuardarEnArchivo archivo = new GuardarEnArchivo();
        archivo.guardar(reporte);

        GuardarEnBD bd = new GuardarEnBD();
        bd.guardar(reporte);

        EnviarPorEmail email = new EnviarPorEmail();
        email.enviar(reporte, "adrian@gmail.com");

        ImprimirReporte impresora = new ImprimirReporte();
        impresora.imprimir(reporte);
    }
}




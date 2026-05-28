# Aplicar uno de los principios SOLID con JAVA

```java
interface SistemaEmpleado {

    void trabajar();
    void marcarEntrada();
    void marcarSalida();
    void generarReporte();
    void calcularSalario();
    void capacitar();

}

class Empleado implements SistemaEmpleado {

    public void trabajar() {

        System.out.println("Trabajando...");
    }

    public void marcarEntrada() {

        System.out.println("Entrada marcada");
    }

    public void marcarSalida() {

        System.out.println("Salida marcada");
    }

    public void generarReporte() {

        System.out.println("Generando reporte...");
    }

    public void calcularSalario() {

        System.out.println("Calculando salario...");
    }

    public void capacitar() {

        System.out.println("Capacitándose...");
    }
}
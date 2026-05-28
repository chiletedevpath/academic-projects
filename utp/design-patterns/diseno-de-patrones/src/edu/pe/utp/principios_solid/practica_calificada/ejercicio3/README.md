# Aplicar uno de los principios SOLID con JAVA

```java
interface MetodoPago {

    void pagar(double monto);
    void reembolsar(double monto);

}

class TarjetaCredito implements MetodoPago {

    @Override
    public void pagar(double monto) {

        System.out.println("Pagando con tarjeta: " + monto);
    }

    @Override
    public void reembolsar(double monto) {

        System.out.println("Reembolsando a tarjeta: " + monto);
    }
}

class Efectivo implements MetodoPago {

    @Override
    public void pagar(double monto) {

        System.out.println("Pagando en efectivo: " + monto);
    }

    @Override
    public void reembolsar(double monto) {

        throw new UnsupportedOperationException("No se puede reembolsar en efectivo");
    }
}

class ProcesadorPago {

    public void procesarReembolso(MetodoPago metodo, double monto) {

        metodo.reembolsar(monto);
    }
}
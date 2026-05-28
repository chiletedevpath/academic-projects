# Aplicar uno de los principios SOLID con JAVA

```java
class Pedido {

    private String tipoCliente;
    private String tipoEnvio;
    private double monto;

    public Pedido(String tipoCliente, String tipoEnvio, double monto) {

        this.tipoCliente = tipoCliente;
        this.tipoEnvio = tipoEnvio;
        this.monto = monto;

    }

    public double calcularTotal() {

        double descuento = 0;

        if (tipoCliente.equals("NORMAL")) {

            descuento = 0.05;

        } else if (tipoCliente.equals("VIP")) {

            descuento = 0.15;

        } else if (tipoCliente.equals("EMPRESA")) {

            descuento = 0.25;
        }

        double costoEnvio = 0;

        if (tipoEnvio.equals("STANDARD")) {

            costoEnvio = 10;

        } else if (tipoEnvio.equals("EXPRESS")) {

            costoEnvio = 25;
        }

        return monto - (monto * descuento) + costoEnvio;
    }
}
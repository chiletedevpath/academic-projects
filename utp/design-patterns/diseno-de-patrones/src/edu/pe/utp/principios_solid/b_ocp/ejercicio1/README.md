# Aplicar uno de los principios SOLID con JAVA

```java
class CalculadoraDescuento {

    public double calcular(String tipoCliente, double total) {

        if (tipoCliente.equals("regular")) {

            return total * 0.9;

        } else if (tipoCliente.equals("vip")) {

            return total * 0.8;

        } else if (tipoCliente.equals("empleado")) {

            return total * 0.7;

        } else {

            return total;
        }
    }
}
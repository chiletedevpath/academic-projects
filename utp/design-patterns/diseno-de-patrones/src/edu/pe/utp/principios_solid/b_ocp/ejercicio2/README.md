# Aplicar uno de los principios SOLID con JAVA

```java
class ProcesadorPago {

    public void procesarPago(String metodo, double monto) {

        if (metodo.equals("tarjeta")) {

            System.out.println("Procesando pago con tarjeta...");
            System.out.println("Comisión: " + (monto * 0.03));

        } 
        else if (metodo.equals("paypal")) {

            System.out.println("Procesando pago con PayPal...");
            System.out.println("Comisión: " + (monto * 0.05));

        } 
        else if (metodo.equals("transferencia")) {

            System.out.println("Procesando transferencia bancaria...");
            System.out.println("Comisión: " + (monto * 0.01));

        } 
        else if (metodo.equals("crypto")) {

            System.out.println("Procesando pago con criptomonedas...");
            System.out.println("Comisión: " + (monto * 0.02));

        } 
        else {

            System.out.println("Método no soportado");
        }
    }
}
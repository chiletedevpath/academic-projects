# Aplicar uno de los principios SOLID con JAVA

```java
interface Trabajador {

    void trabajar();
    void comer();

}

class Humano implements Trabajador {

    public void trabajar() {

        System.out.println("Trabajando...");
    }

    public void comer() {

        System.out.println("Comiendo...");
    }
}

class Robot implements Trabajador {

    public void trabajar() {

        System.out.println("Trabajando...");
    }

    public void comer() {

        throw new UnsupportedOperationException();
    }
}
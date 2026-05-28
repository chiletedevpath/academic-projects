package edu.pe.utp.pc2.ejercicio4;

public class Principal {

    public static void main(String[] args) {

        Pizza pizza = new Pizza.Builder()

                .setSize("Grande")

                .setDough("Delgada")

                .setCheese("Mozzarella")

                .setTopping("Pepperoni")

                .setSauce("BBQ")

                .build();

        pizza.mostrar();
    }
}
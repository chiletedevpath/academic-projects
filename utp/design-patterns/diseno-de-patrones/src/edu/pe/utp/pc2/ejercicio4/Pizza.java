package edu.pe.utp.pc2.ejercicio4;

public class Pizza {

    private String size;
    private String dough;
    private String cheese;
    private String topping;
    private String sauce;

    // Constructor privado
    private Pizza(Builder builder) {

        this.size = builder.size;
        this.dough = builder.dough;
        this.cheese = builder.cheese;
        this.topping = builder.topping;
        this.sauce = builder.sauce;
    }

    public void mostrar() {

        System.out.println("===== PIZZA =====");

        System.out.println("Tamaño: " + size);

        System.out.println("Masa: " + dough);

        System.out.println("Queso: " + cheese);

        System.out.println("Ingrediente: " + topping);

        System.out.println("Salsa: " + sauce);
    }

    static class Builder {

        private String size;
        private String dough;
        private String cheese;
        private String topping;
        private String sauce;

        public Builder setSize(String size) {

            this.size = size;

            return this;
        }

        public Builder setDough(String dough) {

            this.dough = dough;

            return this;
        }

        public Builder setCheese(String cheese) {

            this.cheese = cheese;

            return this;
        }

        public Builder setTopping(String topping) {

            this.topping = topping;

            return this;
        }

        public Builder setSauce(String sauce) {

            this.sauce = sauce;

            return this;
        }

        // Construcción final
        public Pizza build() {

            return new Pizza(this);
        }
    }
}
package edu.pe.utp.patrones_diseno.creacionales.builder;

public class Principal {

    public class Main {

        public static void main(String[] args) {

            Computer pc = new Computer.Builder()

                    .setCpu("Intel i7")

                    .setRam("16GB")

                    .setGpu("RTX 4060")

                    .setStorage("1TB SSD")

                    .build();

            pc.mostrar();
        }
    }
}

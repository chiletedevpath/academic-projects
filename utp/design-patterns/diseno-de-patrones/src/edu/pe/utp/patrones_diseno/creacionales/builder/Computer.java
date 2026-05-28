package edu.pe.utp.patrones_diseno.creacionales.builder;

class Computer {

    private String cpu;
    private String ram;
    private String gpu;
    private String storage;

    // Constructor privado
    private Computer(Builder builder) {

        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.gpu = builder.gpu;
        this.storage = builder.storage;
    }

    public void mostrar() {

        System.out.println("===== COMPUTADORA =====");

        System.out.println("CPU: " + cpu);

        System.out.println("RAM: " + ram);

        System.out.println("GPU: " + gpu);

        System.out.println("Storage: " + storage);
    }

    // ===============================
    // BUILDER
    // ===============================

    static class Builder {

        private String cpu;
        private String ram;
        private String gpu;
        private String storage;

        public Builder setCpu(String cpu) {

            this.cpu = cpu;

            return this;
        }

        public Builder setRam(String ram) {

            this.ram = ram;

            return this;
        }

        public Builder setGpu(String gpu) {

            this.gpu = gpu;

            return this;
        }

        public Builder setStorage(String storage) {

            this.storage = storage;

            return this;
        }

        public Computer build() {

            return new Computer(this);
        }
    }
}
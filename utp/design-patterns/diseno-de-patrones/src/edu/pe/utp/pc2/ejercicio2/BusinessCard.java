package edu.pe.utp.pc2.ejercicio2;

class BusinessCard implements Cloneable {

    String company;
    String style;

    public BusinessCard(String company, String style) {

        this.company = company;
        this.style = style;
    }

    // Metodo para clonar
    public BusinessCard clonar() {

        try {

            return (BusinessCard) super.clone();

        } catch (CloneNotSupportedException e) {

            return null;
        }
    }

    public void mostrar() {

        System.out.println("Empresa: " + company + " | Estilo: " + style);
    }
}


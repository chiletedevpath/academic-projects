package edu.pe.utp.patrones_diseno.creacionales.prototype;

class Enemy implements Cloneable {

    String type;
    int hp;

    public Enemy(String type, int hp) {

        this.type = type;
        this.hp = hp;
    }

    // Método para clonar
    public Enemy clonar() {

        try {

            return (Enemy) super.clone();

        } catch (CloneNotSupportedException e) {

            return null;
        }
    }

    public void mostrar() {

        System.out.println(type + " - HP: " + hp);
    }
}

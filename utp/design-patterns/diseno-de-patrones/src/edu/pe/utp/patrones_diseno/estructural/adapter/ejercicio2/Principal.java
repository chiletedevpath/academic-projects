package edu.pe.utp.patrones_diseno.estructural.adapter.ejercicio2;

public class Principal {
    public static void main(String[] args){

        SistemaCorreo correo = new SistemaCorreo();

       SistemaSMS mensaje = new AdaptadorCorreoASMS(correo);

       mensaje.enviarMensajeSMS("Contesta urgentemente");


    }
}

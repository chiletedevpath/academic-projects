package edu.pe.utp.pc2.ejercicio1;

public class Principal{

            public static void main(String[] args) {

            // Obtener misma instancia
            DatabaseConnection db1 =
                    DatabaseConnection.getInstancia();

            DatabaseConnection db2 =
                    DatabaseConnection.getInstancia();

            db1.connect();

            db2.connect();

            // Verificar misma instancia
            System.out.println(db1 == db2);
        }
    }

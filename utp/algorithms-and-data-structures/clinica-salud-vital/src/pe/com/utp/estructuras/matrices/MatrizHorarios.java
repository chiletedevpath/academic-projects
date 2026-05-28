package pe.com.utp.estructuras.matrices;

import pe.com.utp.modelo.persona.Doctor;

/*
 * Matriz que representa la carga de citas por medico y por dia laboral.
 * Las filas corresponden a medicos asignados y las columnas a dias de atencion.
 */
public class MatrizHorarios {

    private static final int FILAS = 3;        // Tres medicos en la demostracion.
    private static final int COLUMNAS = 5;     // Cinco dias laborables de atencion.

    private int[][] matrizCitas;
    private Doctor[] medicosAsignados;

    /*
     * La posicion de medicosAsignados coincide con la fila de matrizCitas.
     * Ejemplo: medicosAsignados[1] representa a matrizCitas[1][...].
     */
    public MatrizHorarios(Doctor medicoFila1, Doctor medicoFila2, Doctor medicoFila3) {
        matrizCitas = new int[FILAS][COLUMNAS];
        this.medicosAsignados = new Doctor[FILAS];
        medicosAsignados[0] = medicoFila1;
        medicosAsignados[1] = medicoFila2;
        medicosAsignados[2] = medicoFila3;
    }

    public int buscarFilaPorCodigoMedico(String codigo) {
        // Busqueda lineal para ubicar la fila del medico dentro de la matriz.
        for (int i = 0; i < medicosAsignados.length; i++) {
            if (medicosAsignados[i].getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }

        // -1 indica que el medico no fue asignado a esta matriz de horarios.
        return -1;
    }

    // Muestra la matriz de citas medicas en forma de tabla numerica.
    public void mostrarMatriz() {
        System.out.println("\n MATRIZ DE CITAS MEDICAS ");
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(matrizCitas[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Calcula el total de citas por medico. La suma se realiza por filas.
    public void mostrarTotalPorDoctor() {
        System.out.println("\n TOTAL DE CITAS POR DOCTOR ");
        for (int i = 0; i < FILAS; i++) {
            int suma = 0;
            for (int j = 0; j < COLUMNAS; j++) {
                suma += matrizCitas[i][j];
            }
            System.out.println("Doctor " + (i + 1) + ": " + suma + " citas");
        }
    }

    // Calcula el total de citas por dia. La suma se realiza por columnas.
    public void mostrarTotalPorDia() {
        System.out.println("\n TOTAL DE CITAS POR DIA ");
        for (int j = 0; j < COLUMNAS; j++) {
            int suma = 0;

            for (int i = 0; i < FILAS; i++) {
                suma += matrizCitas[i][j];
            }
            System.out.println("Dia " + (j + 1) + ": " + suma + " citas");
        }
    }

    public void registrarCitaEnMatriz(int filaDoctor, int columnaDia) {
        // Protege la matriz evitando accesos fuera de sus limites.
        if (filaDoctor >= 0 && filaDoctor < FILAS && columnaDia >= 0 && columnaDia < COLUMNAS) {
            matrizCitas[filaDoctor][columnaDia]++;
        } else {
            System.out.println("\n Error: indices de matriz fuera de rango.");
        }
    }

    public boolean esPosicionValida(int filaDoctor, int columnaDia) {
        return filaDoctor >= 0 && filaDoctor < FILAS && columnaDia >= 0 && columnaDia < COLUMNAS;
    }
}

package pe.com.utp.servicios;

import pe.com.utp.estructuras.matrices.MatrizHorarios;
import pe.com.utp.modelo.cita.Cita;

public class CitaService {

    // Capacidad maxima del arreglo unidimensional de citas.
    private static final int CAPACIDAD = 20;

    private Cita[] citas;
    private MatrizHorarios matrizHorarios;

    // Cantidad real de citas registradas; evita recorrer posiciones vacias.
    private int totalCitas;

    /*
     * El servicio recibe una matriz ya configurada con sus medicos.
     * Asi se mantiene coherencia entre las citas registradas y los horarios.
     */
    public CitaService(MatrizHorarios matrizHorarios) {
        this.citas = new Cita[CAPACIDAD];
        this.matrizHorarios = matrizHorarios;
        this.totalCitas = 0;
    }

    // Registra una cita en el arreglo y actualiza la matriz de horarios.
    public void registrarCita(Cita nuevaCita, int columnaDia) {

        // La fila se obtiene desde el doctor asociado a la cita.
        String codigoDoctor = nuevaCita.getDoctor().getCodigo();
        int filaEncontrada = matrizHorarios.buscarFilaPorCodigoMedico(codigoDoctor);

        // Si el medico no pertenece a la agenda, se rechaza el registro.
        if (filaEncontrada == -1) {
            System.out.println("\nMedico no asignado");
            return;
        }

        // Antes de almacenar se valida capacidad del arreglo y posicion de matriz.
        if (totalCitas < CAPACIDAD) {

            if (matrizHorarios.esPosicionValida(filaEncontrada, columnaDia - 1)) {
                citas[totalCitas] = nuevaCita;
                totalCitas++;

                // Contabiliza la cita en la fila del medico encontrado.
                matrizHorarios.registrarCitaEnMatriz(filaEncontrada, columnaDia - 1);
                System.out.println("\nCita registrada correctamente");
            } else {
                System.out.println("\nPosicion incorrecta");
            }
        } else {
            System.out.println("\nNo hay espacio para mas citas");
        }
    }

    public Cita buscarCitaPorCodigo(String codigoABuscar) {
        // Busqueda lineal sobre las posiciones ocupadas del arreglo.
        for (int i = 0; i < totalCitas; i++) {
            if (citas[i].getCodigo().equalsIgnoreCase(codigoABuscar)) {
                return citas[i];
            }
        }
        System.out.println("\nLa cita con codigo " + codigoABuscar + " no ha sido encontrada");
        return null;
    }

    // Cancela la cita encontrada cambiando su estado dentro del objeto Cita.
    // La actualizacion de la matriz al cancelar se integrara en una mejora posterior.
    public Cita cancelarCita(String codigoABuscar) {
        for (int i = 0; i < totalCitas; i++) {
            if (citas[i].getCodigo().equalsIgnoreCase(codigoABuscar)) {
                citas[i].cancelar();
                System.out.println("Su cita ha sido cancelada");
                return citas[i];
            }
        }

        System.out.println("No se encontro la cita con codigo: " + codigoABuscar);
        return null;
    }

    public void mostrarCitas() {
        if (totalCitas == 0) {
            System.out.println("No hay citas registradas");
            return;
        }

        for (int i = 0; i < totalCitas; i++) {
            citas[i].mostrarDatos();
            System.out.println("-------------------");
        }
    }

    // Metodos de consulta que delegan los reportes a la matriz de horarios.
    public void mostrarMatrizHorarios() {
        matrizHorarios.mostrarMatriz();
    }

    public void mostrarTotalCitasPorDoctor() {
        matrizHorarios.mostrarTotalPorDoctor();
    }

    public void mostrarTotalCitasPorDia() {
        matrizHorarios.mostrarTotalPorDia();
    }
}

package edu.pe.utp.pc2.ejercicio1.modelo;

/**
 * TAD Libro.
 * Esta clase representa el dato que serÃ¡ almacenado
 * dentro de cada nodo de la lista enlazada.
 * Importante:
 * - El elemento principal de la lista NO es String ni tipo primitivo.
 * - Cada nodo almacena un objeto completo de tipo Libro.
 */
public class Libro {

    private String codigo;
    private String isbn;
    private String titulo;
    private int anioPublicacion;

    /**
     * Constructor principal del TAD Libro.
     */
    public Libro(String codigo, String isbn, String titulo, int anioPublicacion) {
        this.codigo = codigo;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Permite mostrar el contenido del libro de forma clara
     * cuando se imprime la lista enlazada.
     */
    @Override
    public String toString() {
        return "Libro{" + "codigo='" + codigo + '\'' + ", isbn='" + isbn + '\'' + ", titulo='" + titulo + '\'' + ", anioPublicacion=" + anioPublicacion + '}';
    }
}
package com.mycompany.esismicos;
public class AlcanceSismo {

    // Atributos
    private String descripcion;
    private String nombre;

    // Constructor con todos los parámetros
    public AlcanceSismo(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "AlcanceSismo{" +
               "descripcion='" + descripcion + '\'' +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}

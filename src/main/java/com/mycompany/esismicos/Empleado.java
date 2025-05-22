package com.mycompany.esismicos;
public class Empleado {
    // --- Atributos del Empleado ---
    private String nombre;
    private String apellido;
    private String cargo;

    // --- Constructor ---
    public Empleado(String nombre, String apellido, String cargo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
    }

    // --- Getters y Setters ---
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // --- Métodos solicitados (void y vacíos) ---
    public void getNombreCompleto() {
        // Método vacío por solicitud.
    }

    // --- Método toString() (Añadido) ---
    @Override
    public String toString() {
        return "Empleado{" +
               "nombre='" + nombre + '\'' +
               ", apellido='" + apellido + '\'' +
               ", cargo='" + cargo + '\'' +
               '}';
    }
}

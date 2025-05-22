package com.mycompany.esismicos;
import java.time.LocalDateTime;

public class Sesion {
    // --- Atributos de la Sesión ---
    private LocalDateTime fechaHora;
    private Empleado empleado; // Ahora solo tenemos la asociación directa al Empleado

    // --- Constructor ---
    // El constructor ahora solo necesita la fecha/hora y el objeto Empleado
    public Sesion(LocalDateTime fechaHora, Empleado empleado) {
        this.fechaHora = fechaHora;
        this.empleado = empleado; // Asigna el Empleado recibido
    }

    // --- Getters y Setters ---
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    // Getter para obtener el objeto Empleado asociado
    public Empleado getEmpleado() {
        return empleado;
    }

    // Setter para establecer el objeto Empleado asociado
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "Sesion{" +
               "fechaHora=" + fechaHora +
               ", empleado=" + (empleado != null ? empleado.getNombre() : "N/A") + // Muestra algo del empleado si existe
               '}';
    }
}

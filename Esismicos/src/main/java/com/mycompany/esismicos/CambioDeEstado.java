package com.mycompany.esismicos;

import java.time.LocalDateTime; // Importa la clase LocalDateTime

public class CambioDeEstado {

    // Atributos
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    // --- Asociación 1:1 con la clase Estado ---
    private Estado estado; // Un CambioDeEstado se asocia a un solo Estado

    // --- NUEVA Asociación 1:1 con la clase Empleado ---
    private Empleado responsableInspeccion; // Atributo para el empleado responsable de la inspección
    // ----------------------------------------------------

    // Constructor con todos los parámetros
    // Ahora incluye el Empleado responsable de la inspección
    public CambioDeEstado(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Estado estado, Empleado responsableInspeccion) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = estado;
        this.responsableInspeccion = responsableInspeccion; // Inicializa la nueva asociación
    }

    // Getters y Setters
    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // --- Getter y Setter para la Asociación con Empleado (responsableInspeccion) ---
    public Empleado getResponsableInspeccion() {
        return responsableInspeccion;
    }

    public void setResponsableInspeccion(Empleado responsableInspeccion) {
        this.responsableInspeccion = responsableInspeccion;
    }

    // --- Métodos solicitados (void y vacíos) ---

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void esCambioDeEstadoActual() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void actualizarCambioEstadoAbloqueado() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     * (Este método ya existía como setter, pero lo mantengo vacío aquí como un método adicional, si esa es la intención).
     */
    public void setFechaHoraFin() {
        // Método vacío por solicitud. Si este método debería ser el setter de fechaHoraFin,
        // ya existe el setter "public void setFechaHoraFin(LocalDateTime fechaHoraFin)".
        // Se mantiene como un método void sin parámetros y sin acción.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void sosCambioEstadoActual() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void esPendienteRevision() {
        // Método vacío por solicitud.
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        String nombreResponsable = (responsableInspeccion != null ? responsableInspeccion.getNombre() + " " + responsableInspeccion.getApellido() : "N/A");

        return "CambioDeEstado{" +
               "fechaHoraInicio=" + fechaHoraInicio +
               ", fechaHoraFin=" + fechaHoraFin +
               ", estado=" + (estado != null ? estado.getNombreEstado() : "N/A") + // Usamos getNombreEstado()
               ", responsableInspeccion='" + nombreResponsable + '\'' + // Nuevo atributo en toString
               '}';
    }
}

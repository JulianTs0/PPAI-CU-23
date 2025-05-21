import java.time.LocalDateTime; // Importa la clase LocalDateTime

public class CambioDeEstado {

    // Atributos
    private LocalDateTime fechaHoraInicio; // Cambiado a LocalDateTime
    private LocalDateTime fechaHoraFin;    // Cambiado a LocalDateTime

    // --- Asociación 1:1 con la clase Estado ---
    private Estado estado; // Un CambioDeEstado se asocia a un solo Estado
    // ------------------------------------------

    // Constructor con todos los parámetros
    // Ahora recibe LocalDateTime en lugar de String
    public CambioDeEstado(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = estado; // Inicializa la asociación
    }

    // Getters y Setters
    // Los tipos de retorno y parámetro ahora son LocalDateTime
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

    // --- Getter y Setter para la Asociación con Estado ---
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        return "CambioDeEstado{" +
               "fechaHoraInicio=" + fechaHoraInicio + // LocalDateTime se convierte automáticamente a String legible
               ", fechaHoraFin=" + fechaHoraFin +
               ", estado=" + (estado != null ? estado.getNombreEstado() : "N/A") +
               '}';
    }
}

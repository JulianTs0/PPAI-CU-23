public class CambioDeEstado {

    // Atributos
    private String fechaHoraInicio;
    private String fechaHoraFin;

    // --- Asociación 1:1 con la clase Estado ---
    private Estado estado; // Un CambioDeEstado se asocia a un solo Estado
    // ------------------------------------------

    // Constructor con todos los parámetros
    public CambioDeEstado(String fechaHoraInicio, String fechaHoraFin, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = estado; // Inicializa la asociación
    }

    // Getters y Setters
    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
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
               "fechaHoraInicio='" + fechaHoraInicio + '\'' +
               ", fechaHoraFin='" + fechaHoraFin + '\'' +
               ", estado=" + (estado != null ? estado.getNombreEstado() : "N/A") + // Muestra el nombre del estado si no es null
               '}';
    }
}

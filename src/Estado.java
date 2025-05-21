public class Estado {

    // Atributos
    private String ambito;
    private String nombreEstado;

    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    // Getters y Setters
    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    // --- Métodos solicitados (void y vacíos) ---

    public boolean esPendienteDeRevision() {
        return "Pendiente de Revisión".equals(this.nombreEstado);
    }
    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void esEstadoBLoqueadoARevisar() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void esRechazado() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void esAmbitoEventoSismico() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     * (Nota: el método 'esEstadoBloqueadoARevisar()' estaba duplicado en la petición,
     * se mantiene solo una instancia de la definición).
     */
    public void setFechaHoraFin() {
        // Método vacío por solicitud.
    }

    // --- Método toString() (incluido por tu nueva directriz) ---
    @Override
    public String toString() {
        return "Estado{" +
               "ambito='" + ambito + '\'' +
               ", nombreEstado='" + nombreEstado + '\'' +
               '}';
    }
}

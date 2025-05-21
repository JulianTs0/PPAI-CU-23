public class Sesion {
    // --- Atributos de la Sesión ---
    private String fechaHora;
    private String responsable;

    // --- Constructor ---
    public Sesion(String fechaHora, String responsable) {
        this.fechaHora = fechaHora;
        this.responsable = responsable;
    }
    // --- Getters y Setters ---
    public String getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
    public String getResponsable() {
        return responsable;
    }
    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
    // --- Métodos solicitados (void y vacíos) ---
    public void obtenerEmpleado(){
        // Método vacío por solicitud.
    }

    // --- Método toString() (Añadido) ---
    @Override
    public String toString() {
        return "Sesion{" +
               "fechaHora='" + fechaHora + '\'' +
               ", responsable='" + responsable + '\'' +
               '}';
    }
}

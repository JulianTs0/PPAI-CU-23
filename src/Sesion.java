import java.time.LocalDateTime; // Importa la clase LocalDateTime

public class Sesion {
    // --- Atributos de la Sesión ---
    private LocalDateTime fechaHora; // Cambiado a LocalDateTime
    private String responsable;

    // --- Constructor ---
    // Ahora el constructor recibe LocalDateTime
    public Sesion(LocalDateTime fechaHora, String responsable) {
        this.fechaHora = fechaHora;
        this.responsable = responsable;
    }
    // --- Getters y Setters ---
    // El tipo de retorno y parámetro ahora es LocalDateTime
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
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
               "fechaHora=" + fechaHora + // LocalDateTime se imprimirá de forma legible
               ", responsable='" + responsable + '\'' +
               '}';
    }
}

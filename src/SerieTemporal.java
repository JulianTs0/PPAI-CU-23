import java.time.LocalDateTime; // Importa la clase LocalDateTime
import java.util.ArrayList;
import java.util.List;

public class SerieTemporal {

    // Atributos
    private String condicionAlarma;
    private LocalDateTime fechaHoraIncioRegistroMuestras; // Cambiado a LocalDateTime
    private LocalDateTime fechaHoraRegistro;             // Cambiado a LocalDateTime
    private double frecuenciaMuestreo;

    // --- Asociación 1:1 con la clase Estado ---
    private Estado estado;

    // --- Composición 1:M con MuestraSismica ---
    private List<MuestraSismica> muestrasSismicas;

    // Constructor
    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraIncioRegistroMuestras, LocalDateTime fechaHoraRegistro, double frecuenciaMuestreo, Estado estado) { // Cambiado el tipo
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraIncioRegistroMuestras = fechaHoraIncioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.estado = estado;
        this.muestrasSismicas = new ArrayList<>();
    }

    // Getters y Setters de Atributos (tipos actualizados)
    public String getCondicionAlarma() {
        return condicionAlarma;
    }

    public void setCondicionAlarma(String condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }

    public LocalDateTime getFechaHoraIncioRegistroMuestras() {
        return fechaHoraIncioRegistroMuestras;
    }

    public void setFechaHoraIncioRegistroMuestras(LocalDateTime fechaHoraIncioRegistroMuestras) {
        this.fechaHoraIncioRegistroMuestras = fechaHoraIncioRegistroMuestras;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public double getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }

    public void setFrecuenciaMuestreo(double frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }

    // --- Getter y Setter para la Asociación con Estado ---
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // --- Métodos para manejar la Composición con MuestraSismica ---

    /**
     * Obtiene la lista de todas las muestras sísmicas asociadas.
     * @return Una lista de objetos MuestraSismica (copia defensiva).
     */
    public List<MuestraSismica> getMuestrasSismicas() {
        return new ArrayList<>(this.muestrasSismicas);
    }

    /**
     * Añade una MuestraSismica a la serie temporal.
     * @param muestra La MuestraSismica a añadir.
     */
    public void addMuestraSismica(MuestraSismica muestra) {
        if (muestra != null) {
            this.muestrasSismicas.add(muestra);
        }
    }

    /**
     * Crea una nueva MuestraSismica y la asocia a esta SerieTemporal.
     * Este método encapsula la creación de la parte (MuestraSismica) por el todo (SerieTemporal),
     * lo cual es característico de la composición.
     * @param fechaHoraMuestra La fecha y hora de la muestra.
     */
    public void crearMuestraSismica(LocalDateTime fechaHoraMuestra) { // Cambiado el tipo de parámetro
        MuestraSismica nuevaMuestra = new MuestraSismica(fechaHoraMuestra);
        this.muestrasSismicas.add(nuevaMuestra);
    }

    // --- Métodos solicitados (void y vacíos) ---

    public void getDatos() {
        // Este método está intencionalmente vacío.
    }

    public void obtenerDatosMuestraSismica() {
        // Este método está intencionalmente vacío.
    }

    public void buscarSismografo() {
        // Este método está intencionalmente vacío.
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "SerieTemporal{" +
               "condicionAlarma='" + condicionAlarma + '\'' +
               ", fechaHoraIncioRegistroMuestras=" + fechaHoraIncioRegistroMuestras +:
               ", fechaHoraRegistro=" + fechaHoraRegistro +
               ", frecuenciaMuestreo=" + frecuenciaMuestreo +
               ", estado=" + (estado != null ? estado.getNombreEstado() : "N/A") +
               ", numMuestrasSismicas=" + muestrasSismicas.size() +
               '}';
    }
}

package com.mycompany.esismicos;
import java.time.LocalDateTime; // Importa la clase LocalDateTime
import java.util.ArrayList;
import java.util.List;

public class Sismografo {

    // Atributos
    private LocalDateTime fechaAdquisicion; // Cambiado a LocalDateTime
    private String identificadorSismografo;
    private String nroSerie;
    private String nombre;

    // --- Asociaciones ---

    // Asociación 1:1 con EstacionSismologica
    private EstacionSismologica estacionSismologica;

    // Asociación 1:1 con Estado, con atributo referencial "estadoActual"
    private Estado estadoActual;

    // Asociación 1:M con CambioDeEstado
    private List<CambioDeEstado> cambiosDeEstado;

    // Asociación 1:M con SerieTemporal
    private List<SerieTemporal> seriesTemporales;

    // --- Constructor Principal ---

    // Constructor con todos los parámetros principales y asociaciones 1:1
    public Sismografo(LocalDateTime fechaAdquisicion, String identificadorSismografo, // Cambiado el tipo
                      String nroSerie, String nombre,
                      EstacionSismologica estacionSismologica, Estado estadoActual) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.nombre = nombre;
        this.estacionSismologica = estacionSismologica;
        this.estadoActual = estadoActual;
        this.cambiosDeEstado = new ArrayList<>();
        this.seriesTemporales = new ArrayList<>();
    }

    // --- Getters y Setters de Atributos (tipo actualizado) ---

    public LocalDateTime getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDateTime fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setIdentificadorSismografo(String identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // --- Getters y Setters de Asociaciones 1:1 ---

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    // --- Métodos para manejar Asociaciones 1:M ---

    // Getter para la lista de CambiosDeEstado (retorna copia defensiva)
    public List<CambioDeEstado> getCambiosDeEstado() {
        return new ArrayList<>(this.cambiosDeEstado);
    }

    // Método para agregar un CambioDeEstado
    public void addCambioDeEstado(CambioDeEstado cambio) {
        if (cambio != null) {
            this.cambiosDeEstado.add(cambio);
        }
    }

    // Getter para la lista de SeriesTemporales (retorna copia defensiva)
    public List<SerieTemporal> getSeriesTemporales() {
        return new ArrayList<>(this.seriesTemporales);
    }

    // Método para agregar una SerieTemporal
    public void addSerieTemporal(SerieTemporal serie) {
        if (serie != null) {
            this.seriesTemporales.add(serie);
        }
    }

    // --- Métodos solicitados (vacíos y void) ---

    public void setEstadoActual() {
        // Método vacío por solicitud.
    }

    public void soyTuSerieTemporal() {
        // Método vacío por solicitud.
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "Sismografo{" +
               "identificadorSismografo='" + identificadorSismografo + '\'' +
               ", nombre='" + nombre + '\'' +
               ", nroSerie='" + nroSerie + '\'' +
               ", fechaAdquisicion=" + fechaAdquisicion + // LocalDateTime se imprimirá de forma legible
               ", estacionSismologica=" + (estacionSismologica != null ? estacionSismologica.getNombre() : "N/A") +
               ", estadoActual=" + (estadoActual != null ? estadoActual.getNombreEstado() : "N/A") +
               ", numCambiosDeEstado=" + cambiosDeEstado.size() +
               ", numSeriesTemporales=" + seriesTemporales.size() +
               '}';
    }
}

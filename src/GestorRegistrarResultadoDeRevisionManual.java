import java.time.LocalDateTime;

public class GestorRegistrarResultadoDeRevisionManual {
    // --- Atributos del Gestor ---
    private LocalDateTime fechaHoraOcurrencia;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double magnitud;
    private LocalDateTime fechaHoraBloqueado;
    private String nombreAlcance;
    private String nombreClasificacion;
    private String nombreOrigen;
    private LocalDateTime fechaHoraRevision;

    // Constructor
    public GestorRegistrarResultadoDeRevisionManual(
            LocalDateTime fechaHoraOcurrencia,
            double latitudEpicentro,
            double longitudEpicentro,
            double magnitud,
            LocalDateTime fechaHoraBloqueado,
            String nombreAlcance,
            String nombreClasificacion,
            String nombreOrigen,
            LocalDateTime fechaHoraRevision) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudEpicentro = latitudEpicentro;
        this.longitudEpicentro = longitudEpicentro;
        this.magnitud = magnitud;
        this.fechaHoraBloqueado = fechaHoraBloqueado;
        this.nombreAlcance = nombreAlcance;
        this.nombreClasificacion = nombreClasificacion;
        this.nombreOrigen = nombreOrigen;
        this.fechaHoraRevision = fechaHoraRevision;
    }

    // --- Getters y Setters ---

    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(LocalDateTime fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public double getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public void setLatitudEpicentro(double latitudEpicentro) {
        this.latitudEpicentro = latitudEpicentro;
    }

    public double getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public void setLongitudEpicentro(double longitudEpicentro) {
        this.longitudEpicentro = longitudEpicentro;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public LocalDateTime getFechaHoraBloqueado() {
        return fechaHoraBloqueado;
    }

    public void setFechaHoraBloqueado(LocalDateTime fechaHoraBloqueado) {
        this.fechaHoraBloqueado = fechaHoraBloqueado;
    }

    public String getNombreAlcance() {
        return nombreAlcance;
    }

    public void setNombreAlcance(String nombreAlcance) {
        this.nombreAlcance = nombreAlcance;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public LocalDateTime getFechaHoraRevision() {
        return fechaHoraRevision;
    }

    public void setFechaHoraRevision(LocalDateTime fechaHoraRevision) {
        this.fechaHoraRevision = fechaHoraRevision;
    }

    // --- Métodos del Gestor (vacíos) ---

    public void registrarNuevaRevisionManual() {}
    public void buscarEventosSismicosAutodetectadosNoRevisados() {}
    public void ordenarFechaHoraDeOcurrencia() {}
    public void tomarEventoSismicoARevisar() {}
    public void buscarSesionActual() {}
    public void getFechaActual() {}
    public void buscarEstadoBloqueadoEnRevision() {}
    public void buscarDatosDelEventoSismicoSeleccionado() {}
    public void clasificarDatosPorEstacionSismologica() {}
    public void tomarOpcionVisualizarMapa() {}
    public void tomarOpcionModificarDatos() {}
    public void tomarOpcionesDeEvento() {}
    public void validarMagnitud() {}
    public void validarAlcance() {}
    public void validarOrigenDeGen() {}
    public void validarAccion() {}
    public void buscarEstadoRechazado() {}
    public void obtenerEventosAutoDetectadosNoRevisados() {}
    public void finCU() {}

    // --- Método toString() ---
    @Override
    public String toString() {
        return "GestorRegistrarResultadoDeRevisionManual{" +
               "fechaHoraOcurrencia=" + fechaHoraOcurrencia +
               ", latitudEpicentro=" + latitudEpicentro +
               ", longitudEpicentro=" + longitudEpicentro +
               ", magnitud=" + magnitud +
               ", fechaHoraBloqueado=" + fechaHoraBloqueado +
               ", nombreAlcance='" + nombreAlcance + '\'' +
               ", nombreClasificacion='" + nombreClasificacion + '\'' +
               ", nombreOrigen='" + nombreOrigen + '\'' +
               ", fechaHoraRevision=" + fechaHoraRevision +
               '}';
    }
}

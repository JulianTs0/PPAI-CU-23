import java.time.LocalDateTime;

public class GestorRegistrarResultadoDeRevisionManual {
    // --- Atributos del Gestor ---
    private String fechaHoraOcurrencia;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double magnitud;
    private String fechaHoraBloqueado;
    private String nombreAlcance;
    private String nombreClasificacion;
    private String nombreOrigen;
    private String fechaHoraRevision;

    public GestorRegistrarResultadoDeRevisionManual(String fechaHoraOcurrencia, double latitudEpicentro, double longitudEpicentro,
            double magnitud, String fechaHoraBloqueado, String nombreAlcance, String nombreClasificacion, String nombreOrigen,
            String fechaHoraRevision) {
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
    

    // --- Métodos del Gestor ---

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






    

    // Getters y setters según necesidad
    // ...
}
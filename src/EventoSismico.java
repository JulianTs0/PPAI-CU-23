import java.util.ArrayList;
import java.util.List;

public class EventoSismico {

    // Atributos
    private String fechaHoraFin;
    private String fechaHoraOcurrencia;
    private double latitudHipocentro;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double longitudHipocentro;
    private double valorMagnitud;
    private String nombreAlcance; // Asumo que estos son atributos de String y no asociaciones directas por el nombre
    private String nombreClasificacion; // Asumo String y no asociación directa
    private String nombreOrigen;    // Asumo String y no asociación directa

    // --- Asociaciones 1:1 ---
    private ClasificacionSismo clasificacionSismo; // Asociación con ClasificacionSismo
    private OrigenDeGeneracion origenSismo;             // Asociación con OrigenSismo
    private AlcanceSismo alcanceSismo;           // Asociación con AlcanceSismo
    private Estado estadoActual;                 // Asociación con Estado (atributo referencial)

    // --- Asociaciones 1:M ---
    private List<SerieTemporal> seriesTemporales;     // Asociación con SerieTemporal
    private List<CambioDeEstado> cambiosDeEstado;   // Asociación con CambioDeEstado

    // Constructor
    public EventoSismico(String fechaHoraFin, String fechaHoraOcurrencia,
                         double latitudHipocentro, double latitudEpicentro,
                         double longitudEpicentro, double longitudHipocentro,
                         double valorMagnitud, String nombreAlcance,
                         String nombreClasificacion, String nombreOrigen,
                         ClasificacionSismo clasificacionSismo, OrigenDeGeneracion origenSismo,
                         AlcanceSismo alcanceSismo, Estado estadoActual) {
        this.fechaHoraFin = fechaHoraFin;
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudHipocentro = latitudHipocentro;
        this.latitudEpicentro = latitudEpicentro;
        this.longitudEpicentro = longitudEpicentro;
        this.longitudHipocentro = longitudHipocentro;
        this.valorMagnitud = valorMagnitud;
        this.nombreAlcance = nombreAlcance;
        this.nombreClasificacion = nombreClasificacion;
        this.nombreOrigen = nombreOrigen;
        this.clasificacionSismo = clasificacionSismo;
        this.origenSismo = origenSismo;
        this.alcanceSismo = alcanceSismo;
        this.estadoActual = estadoActual;
        this.seriesTemporales = new ArrayList<>();   // Inicializa la lista para 1:M
        this.cambiosDeEstado = new ArrayList<>();    // Inicializa la lista para 1:M
    }

    // --- Getters y Setters de Atributos ---

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(String fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }

    public double getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public void setLatitudHipocentro(double latitudHipocentro) {
        this.latitudHipocentro = latitudHipocentro;
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

    public double getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public void setLongitudHipocentro(double longitudHipocentro) {
        this.longitudHipocentro = longitudHipocentro;
    }

    public double getValorMagnitud() {
        return valorMagnitud;
    }

    public void setValorMagnitud(double valorMagnitud) {
        this.valorMagnitud = valorMagnitud;
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

    // --- Getters y Setters de Asociaciones 1:1 ---

    public ClasificacionSismo getClasificacionSismo() {
        return clasificacionSismo;
    }

    public void setClasificacionSismo(ClasificacionSismo clasificacionSismo) {
        this.clasificacionSismo = clasificacionSismo;
    }

    public OrigenDeGeneracion getOrigenDeGeneracion() {
        return origenSismo;
    }

    public void setOrigenSismo(OrigenDeGeneracion origenSismo) {
        this.origenSismo = origenSismo;
    }

    public AlcanceSismo getAlcanceSismo() {
        return alcanceSismo;
    }

    public void setAlcanceSismo(AlcanceSismo alcanceSismo) {
        this.alcanceSismo = alcanceSismo;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    // --- Métodos para manejar Asociaciones 1:M ---

    public List<SerieTemporal> getSeriesTemporales() {
        return new ArrayList<>(this.seriesTemporales); // Copia defensiva
    }

    public void addSerieTemporal(SerieTemporal serieTemporal) {
        if (serieTemporal != null) {
            this.seriesTemporales.add(serieTemporal);
        }
    }

    public List<CambioDeEstado> getCambiosDeEstado() {
        return new ArrayList<>(this.cambiosDeEstado); // Copia defensiva
    }

    public void addCambioDeEstado(CambioDeEstado cambioDeEstado) {
        if (cambioDeEstado != null) {
            this.cambiosDeEstado.add(cambioDeEstado);
        }
    }

    // --- Métodos solicitados (void y vacíos) ---

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void esPendienteDeRevision() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void obtenerUbicacion() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void buscarCambioDeEstadoActual() {
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
    public void buscarDatosDelEventoSismicoSeleccionado() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void obtenerDatosSerieTemporal() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void getSerieTemporal() {
        // Método vacío por solicitud.
        // Nota: ya existe un getSeriesTemporales() que devuelve la lista.
        // Este es un método separado y vacío según la petición.
    }

    // El método obtenerDatosSerieTemporal() estaba duplicado en la petición, se mantiene solo una instancia.

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void rechazar() {
        // Método vacío por solicitud.
    }

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
    public void bloquearEnRevision() {
        // Método vacío por solicitud.
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void crearCambioEstado() {
        // Método vacío por solicitud.
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "EventoSismico{" +
               "fechaHoraFin='" + fechaHoraFin + '\'' +
               ", fechaHoraOcurrencia='" + fechaHoraOcurrencia + '\'' +
               ", latitudHipocentro=" + latitudHipocentro +
               ", latitudEpicentro=" + latitudEpicentro +
               ", longitudEpicentro=" + longitudEpicentro +
               ", longitudHipocentro=" + longitudHipocentro +
               ", valorMagnitud=" + valorMagnitud +
               ", nombreAlcance='" + nombreAlcance + '\'' +
               ", nombreClasificacion='" + nombreClasificacion + '\'' +
               ", nombreOrigen='" + nombreOrigen + '\'' +
               ", clasificacionSismo=" + (clasificacionSismo != null ? clasificacionSismo.getNombre() : "N/A") +
               ", origenSismo=" + (origenSismo != null ? origenSismo.getNombre() : "N/A") +
               ", alcanceSismo=" + (alcanceSismo != null ? alcanceSismo.getNombre() : "N/A") +
               ", estadoActual=" + (estadoActual != null ? estadoActual.getNombreEstado() : "N/A") +
               ", numSeriesTemporales=" + seriesTemporales.size() +
               ", numCambiosDeEstado=" + cambiosDeEstado.size() +
               '}';
    }
}

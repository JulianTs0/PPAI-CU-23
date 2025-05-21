import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorRegistrarResultadoDeRevisionManual {
    private List<LocalDateTime> fechasHorasOcurrencia;
    private List<double[]> ubicacionesEpicentroHipocentro; // Almacenará [latEpicentro, lonEpicentro, latHipocentro, lonHipocentro]
    private List<Double> magnitudes;

    // --- Atributos del Gestor ---

    private String nombreAlcance;
    private String nombreClasificacion;
    private String nombreOrigen;
    private LocalDateTime fechaHoraRevision; 

    private PantallaRegistrarResultadoDeRevisionManual pantalla;
    private List<EventoSismico> todosLosEventosDelSistema; // NUEVO: Atributo para almacenar todos los eventos


    // Constructor
    public GestorRegistrarResultadoDeRevisionManual(
            String nombreAlcance,
            String nombreClasificacion,
            String nombreOrigen,
            LocalDateTime fechaHoraRevision,
            PantallaRegistrarResultadoDeRevisionManual pantalla,
            List<EventoSismico> todosLosEventosDelSistema) { // NUEVO PARÁMETRO
        this.nombreAlcance = nombreAlcance;
        this.nombreClasificacion = nombreClasificacion;
        this.nombreOrigen = nombreOrigen;
        this.fechaHoraRevision = fechaHoraRevision;

        this.fechasHorasOcurrencia = new ArrayList<>();
        this.ubicacionesEpicentroHipocentro = new ArrayList<>();
        this.magnitudes = new ArrayList<>();

        this.pantalla = pantalla;
        this.todosLosEventosDelSistema = todosLosEventosDelSistema; // Asigna la lista de eventos
        System.out.println("GestorRegistrarResultadoDeRevisionManual: Instancia (Constructor Largo) creada con valores iniciales y referencia a Pantalla y Eventos.");
    }

    // --- Getters y Setters ---

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

    public List<LocalDateTime> getFechasHorasOcurrencia() {
        return fechasHorasOcurrencia;
    }

    public List<double[]> getUbicacionesEpicentroHipocentro() {
        return ubicacionesEpicentroHipocentro;
    }

    public List<Double> getMagnitudes() {
        return magnitudes;
    }

    // --- Métodos del Gestor (vacíos) ---

    public void registrarNuevaRevisionManual() {
        System.out.println("GestorRegistrarResultadoDeRevisionManual: Método registrarNuevaRevisionManual ejecutado.");
        if (pantalla != null) {
            pantalla.actualizarEstadoPantalla("Iniciando búsqueda de eventos pendientes...");
        }

        // El gestor ahora opera sobre la lista de eventos que YA LE FUE INYECTADA
        buscarEventosSismicosAutodetectadosNoRevisados(); // Ya no necesita pasar la lista como argumento

        if (pantalla != null) {
            pantalla.actualizarEstadoPantalla("Eventos pendientes cargados. Hay " + fechasHorasOcurrencia.size() + " eventos para revisar.");
            // Aquí el Gestor debería pedirle a la Pantalla que muestre estos datos en la grilla.
            // Por ejemplo: pantalla.mostrarDatosEnGrilla(fechasHorasOcurrenciaList, ubicacionesEpicentroHipocentroList, magnitudesList);
      }
    }

    public void buscarEventosSismicosAutodetectadosNoRevisados() {
        // Limpiar listas antes de cada búsqueda
        this.fechasHorasOcurrencia.clear();
        this.ubicacionesEpicentroHipocentro.clear();
        this.magnitudes.clear();

        System.out.println("Gestor: Buscando eventos sísmicos autodetectados no revisados...");

        // Usa el atributo todosLosEventosDelSistema que fue inyectado
        if (this.todosLosEventosDelSistema == null || this.todosLosEventosDelSistema.isEmpty()) {
            System.out.println("Gestor: No se encontraron eventos sísmicos en el sistema.");
            return;
        }

        for (EventoSismico evento : this.todosLosEventosDelSistema) { // Itera sobre el atributo
            System.out.println("Gestor: Evaluando evento: " + evento.toString());

            boolean esPendiente = evento.esPendienteDeRevision();

            if (!esPendiente) {
                System.out.println("Gestor: Evento NO está pendiente de revisión. Saltando iteración.");
                continue;
            }

            System.out.println("Gestor: Evento SÍ está pendiente de revisión. Procesando datos...");

            LocalDateTime fechaHora = evento.getFechaHoraOcurrencia();
            this.fechasHorasOcurrencia.add(fechaHora);
            System.out.println("Gestor: Fecha y Hora Ocurrencia: " + fechaHora);

            double[] ubicacion = evento.obtenerUbicacion();
            this.ubicacionesEpicentroHipocentro.add(ubicacion);
            System.out.println("Gestor: Ubicación [LatEpic, LonEpic, LatHip, LonHip]: " + ubicacion[0] + ", " + ubicacion[1] + ", " + ubicacion[2] + ", " + ubicacion[3]);

            double magnitudEvento = evento.getValorMagnitud();
            this.magnitudes.add(magnitudEvento);
            System.out.println("Gestor: Magnitud: " + magnitudEvento);

            System.out.println("Gestor: Datos de evento sísmico pendiente de revisión guardados.");
        }
        System.out.println("Gestor: Finalizada la búsqueda de eventos pendientes.");
    }

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
               ", nombreAlcance='" + nombreAlcance + '\'' +
               ", nombreClasificacion='" + nombreClasificacion + '\'' +
               ", nombreOrigen='" + nombreOrigen + '\'' +
               ", fechaHoraRevision=" + fechaHoraRevision +
               '}';
    }
}

package com.mycompany.esismicos;

import com.mycompany.esismicos.ClasificacionSismo;
import java.time.LocalDateTime; // Importa la clase LocalDateTime
import java.util.ArrayList;
import java.util.List;

public class EventoSismico {

    // Atributos
    private LocalDateTime fechaHoraFin;       // Cambiado a LocalDateTime
    private LocalDateTime fechaHoraOcurrencia; // Cambiado a LocalDateTime
    private double latitudHipocentro;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double longitudHipocentro;
    private double valorMagnitud;
    //eventosParagrilla ?
    private String nombreAlcance;
    private String nombreClasificacion;
    private String nombreOrigen;

    // --- Asociaciones 1:1 ---
    private ClasificacionSismo clasificacionSismo;
    private OrigenDeGeneracion origenSismo;
    private AlcanceSismo alcanceSismo;
    private Estado estadoActual;

    // --- Asociaciones 1:M ---
    private List<SerieTemporal> seriesTemporales;
    private List<CambioDeEstado> cambiosDeEstado;

    // Constructor
    public EventoSismico(LocalDateTime fechaHoraFin, LocalDateTime fechaHoraOcurrencia, // Cambiados los tipos
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
        this.seriesTemporales = new ArrayList<>();
        this.cambiosDeEstado = new ArrayList<>();
    }

    // --- Getters y Setters de Atributos (tipos actualizados) ---

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public LocalDateTime getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(LocalDateTime fechaHoraOcurrencia) {
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
        return new ArrayList<>(this.seriesTemporales);
    }

    public void addSerieTemporal(SerieTemporal serieTemporal) {
        if (serieTemporal != null) {
            this.seriesTemporales.add(serieTemporal);
        }
    }

    public List<CambioDeEstado> getCambiosDeEstado() {
        return new ArrayList<>(this.cambiosDeEstado);
    }

    public void addCambioDeEstado(CambioDeEstado cambioDeEstado) {
        if (cambioDeEstado != null) {
            this.cambiosDeEstado.add(cambioDeEstado);
        }
    }

    // --- Métodos solicitados (void y vacíos) ---

   public boolean esPendienteDeRevision() {
        // Ejecuta el método esPendienteDeRevision() de su instancia de Estado
        if (estadoActual != null) {
            return estadoActual.esPendienteDeRevision();
        }
        return false; // Si no hay estado, no está pendiente de revisión
    }

    public double[] obtenerUbicacion() {
        return new double[]{
            this.latitudEpicentro,
            this.longitudEpicentro,
            this.latitudHipocentro,
            this.longitudHipocentro
        };
    }

    /**
     * Busca y retorna el CambioDeEstado actual (aquel con fechaHoraFin nula).
     * Setea la fechaHoraFin de este cambio de estado.
     * @param fechaFin La fecha y hora para setear en el cambio de estado actual.
     * @return El CambioDeEstado actual que fue modificado, o null si no se encontró.
     */
    public CambioDeEstado buscarCambioDeEstadoActual(LocalDateTime fechaFin) {
        System.out.println("EventoSismico: Buscando el cambio de estado actual para el evento: " + this.fechaHoraOcurrencia);
        for (CambioDeEstado cambio : this.cambiosDeEstado) {
            // Usa el método sosCambioEstadoActual() del CambioDeEstado
            if (cambio.sosCambioEstadoActual()) {
                System.out.println("EventoSismico: Cambio de estado actual encontrado: " + cambio.getEstado().getNombreEstado());
                // Setear la fechaHoraFin del cambio de estado actual
                cambio.setFechaHoraFin(fechaFin);
                System.out.println("EventoSismico: Fecha y hora fin '" + fechaFin + "' establecida en el cambio de estado actual.");
                return cambio; // Corta el bucle y retorna el cambio encontrado
            }
        }
        System.out.println("EventoSismico: No se encontró un cambio de estado actual (con fechaHoraFin nula).");
        return null; // Si no se encuentra ningún cambio de estado actual
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
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void rechazar() {
        // Método vacío por solicitud.
    }

    /**
     * Este método estaba duplicado en la petición, se mantiene solo una instancia.
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void esCambioDeEstadoActual() {
        // Método vacío por solicitud.
    }

    /**
     * Implementa la lógica para "bloquear" el evento en revisión.
     * Finaliza el cambio de estado actual del evento y crea uno nuevo
     * con el estado "Bloqueado A Revisar".
     * @param fechaHoraBloqueado La fecha y hora en que se bloquea el evento.
     * @param logeadoEmpleado El empleado que realiza el bloqueo.
     * @param estadoBloqueadoARevisar La instancia del estado "Bloqueado A Revisar".
     */
    public void bloquearEnRevision(LocalDateTime fechaHoraBloqueado, Empleado logeadoEmpleado, Estado estadoBloqueadoARevisar) {
        System.out.println("\n--- EventoSismico: Método bloquearEnRevision() ejecutado ---");
        System.out.println("EventoSismico: Bloqueando evento " + this.getFechaHoraOcurrencia() + "...");

        // 1. Ejecutar buscarCambioDeEstadoActual() para finalizar el cambio de estado previo
        // Se pasa fechaHoraBloqueado para setear la fechaFin del estado actual.
        CambioDeEstado cambioAnterior = this.buscarCambioDeEstadoActual(fechaHoraBloqueado);

        if (cambioAnterior != null) {
            System.out.println("EventoSismico: El cambio de estado anterior a '" + cambioAnterior.getEstado().getNombreEstado() + "' ha sido finalizado.");
        } else {
            System.err.println("EventoSismico: ADVERTENCIA - No se pudo finalizar el cambio de estado anterior.");
            return;
        }

        // 2. Proseguir ejecutando crearCambioEstado()
        // Pasa fechaHoraBloqueado como fecha de inicio del nuevo cambio de estado
        this.crearCambioEstado(fechaHoraBloqueado, estadoBloqueadoARevisar, logeadoEmpleado);

        System.out.println("EventoSismico: Evento " + this.getFechaHoraOcurrencia() +
                " ahora en estado: " + this.getEstadoActual().getNombreEstado() +
                " (Bloqueado por: " + logeadoEmpleado.getNombreCompleto() +
                " en " + fechaHoraBloqueado + ")");
        System.out.println("--- Fin del método bloquearEnRevision() del EventoSismico ---");
    }

    /**
     * Este método está definido pero no realiza ninguna acción.
     * Su implementación se espera en el futuro.
     */
    public void crearCambioEstado(LocalDateTime fechaHoraInicio, Estado estado, Empleado empleado) {
        System.out.println("EventoSismico: Creando un nuevo CambioDeEstado a '" + estado.getNombreEstado() + "'.");
        // Se crea un nuevo CambioDeEstado con fechaHoraFin nula (porque es el estado actual)
        CambioDeEstado nuevoCambio = new CambioDeEstado(fechaHoraInicio, null, estado, empleado);
        this.addCambioDeEstado(nuevoCambio); // Añade el nuevo cambio a la lista
        this.setEstadoActual(estado); // Actualiza el estado actual del evento
        System.out.println("EventoSismico: Nuevo CambioDeEstado creado y añadido. Evento ahora en estado: " + this.getEstadoActual().getNombreEstado());
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "EventoSismico{" +
               "fechaHoraFin=" + fechaHoraFin +
               ", fechaHoraOcurrencia=" + fechaHoraOcurrencia +
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

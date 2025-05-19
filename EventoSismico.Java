public class EventoSismico {

    // Atributos
    private String fechaHoraFin;
    private String fechaHoraOcurrencia;
    private double latitudHipocentro;
    private double latitudEpicentro;
    private double longitudEpicentro;
    private double longitudHipocentro;
    private double valorMagnitud;
    private String nombreAlcance;
    private String nombreClasificacion;
    private String nombreOrigen;

    // Getters
    public String getFechaHoraOcurrencia() {
        return fechaHoraOcurrencia;
    }

    public double getLatitudEpicentro() {
        return latitudEpicentro;
    }

    public double getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public double getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public double getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public double getValorMagnitud() {
        return valorMagnitud;
    }

    public String getAlcance() {
        return nombreAlcance;
    }

    public String getClasificacion() {
        return nombreClasificacion;
    }

    public String getOrigen() {
        return nombreOrigen;
    }

    // Métodos vacíos
    public void esPendienteDeRevision() {
        // TODO: Implementar
    }

    public void uobtenerUbicacion() {
        // TODO: Implementar
    }

    public void buscarCambioDeEstadoActual() {
        // TODO: Implementar
    }

    public void actualizarCambioEstadoAbloqueado() {
        // TODO: Implementar
    }

    public void buscarDatosDelEventoSismicoSeleccionado() {
        // TODO: Implementar
    }

    public void obtenerDatosSerieTemporal() {
        // TODO: Implementar
    }

    public void getSerieTemporal() {
        // TODO: Implementar
    }

    public void obtenerDatosSerieTemporal() { // Este método está duplicado con el anterior, verificar si es intencional
        // TODO: Implementar
    }

    public void rechazar() {
        // TODO: Implementar
    }

    public void esCambioDeEstadoActual() {
        // TODO: Implementar
    }

    public void bloquearEnRevision() {
        // TODO: Implementar
    }

    public void crearCambioEstado() {
        // TODO: Implementar
    }

    // Método que no tiene sentido como método de instancia estándar en Java
    public void newMethod() { // Cambié "new()" por "newMethod()" ya que "new" es palabra reservada
        // TODO: Implementar
    }
}

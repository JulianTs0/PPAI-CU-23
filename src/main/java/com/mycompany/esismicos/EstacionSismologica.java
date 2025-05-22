package com.mycompany.esismicos;
import java.time.LocalDateTime; // Cambiado de LocalDate a LocalDateTime

public class EstacionSismologica {

    // Atributos
    private String codigoEstacion;
    private String documentoCertificacionAdquirido;
    private LocalDateTime fechaSolicitudCertificacion; // Cambiado a LocalDateTime
    private double latitud;
    private double longitud;
    private String nombre;
    private String nroCertificacionAdquisicion;

    // Constructor con todos los parámetros
    public EstacionSismologica(String codigoEstacion, String documentoCertificacionAdquirido,
                               LocalDateTime fechaSolicitudCertificacion, double latitud, double longitud,
                               String nombre, String nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionAdquirido = documentoCertificacionAdquirido;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    // Getters y Setters
    public String getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public String getDocumentoCertificacionAdquirido() {
        return documentoCertificacionAdquirido;
    }

    public void setDocumentoCertificacionAdquirido(String documentoCertificacionAdquirido) {
        this.documentoCertificacionAdquirido = documentoCertificacionAdquirido;
    }

    // El tipo de retorno y parámetro ahora es LocalDateTime
    public LocalDateTime getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }

    public void setFechaSolicitudCertificacion(LocalDateTime fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }

    public void setNroCertificacionAdquisicion(String nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "EstacionSismologica{" +
               "codigoEstacion='" + codigoEstacion + '\'' +
               ", nombre='" + nombre + '\'' +
               ", latitud=" + latitud +
               ", longitud=" + longitud +
               ", fechaSolicitudCertificacion=" + fechaSolicitudCertificacion + // Se imprimirá como LocalDateTime
               '}';
    }
}

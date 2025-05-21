public class EstacionSismologica {

    // Atributos
    private String codigoEstacion;
    private String documentoCertificacionAdquirido;
    private String fechaSolicitudCertificacion; // Usamos LocalDate para fechas
    private double latitud;
    private double longitud;
    private String nombre;
    private String nroCertificacionAdquisicion;

    // Constructor con todos los parámetros
    public EstacionSismologica(String codigoEstacion, String documentoCertificacionAdquirido,
                               LocalDate fechaSolicitudCertificacion, double latitud, double longitud,
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

    public String getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }

    public void setFechaSolicitudCertificacion(String fechaSolicitudCertificacion) {
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

}

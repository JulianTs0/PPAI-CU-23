public class ClasificacionSismo {

    // Atributos
    private double kmProfundidadDesde;
    private double kmProfundidadHasta;
    private String nombre;

    // Constructor vacío
    public ClasificacionSismo() {}

    // Constructor con todos los parámetros
    public ClasificacionSismo(double kmProfundidadDesde, double kmProfundidadHasta, String nombre) {
        this.kmProfundidadDesde = kmProfundidadDesde;
        this.kmProfundidadHasta = kmProfundidadHasta;
        this.nombre = nombre;
    }

    // Getters y Setters
    public double getKmProfundidadDesde() {
        return kmProfundidadDesde;
    }

    public void setKmProfundidadDesde(double kmProfundidadDesde) {
        this.kmProfundidadDesde = kmProfundidadDesde;
    }

    public double getKmProfundidadHasta() {
        return kmProfundidadHasta;
    }

    public void setKmProfundidadHasta(double kmProfundidadHasta) {
        this.kmProfundidadHasta = kmProfundidadHasta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return "ClasificacionSismo{" +
                "kmProfundidadDesde=" + kmProfundidadDesde +
                ", kmProfundidadHasta=" + kmProfundidadHasta +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

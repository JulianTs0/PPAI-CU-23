public class TipoDeDato {

    // Atributos
    private String denominacion;
    private String nombreUnidadMedida;

    // Constructor (Nota: el nombre 'new' es una palabra reservada en Java.
    // Usaremos un nombre convencional como 'TipoDeDato' para el constructor,
    // o 'nuevoTipoDeDato' si se buscaba algo similar a la función 'new' en otros contextos).
    // Si el requisito es estrictamente 'new()', no se puede, pero si te refieres
    // a la función de creación de objetos, el nombre del constructor debe coincidir con la clase.
    // Opto por el nombre estándar de constructor para la clase.
    public TipoDeDato(String denominacion, String nombreUnidadMedida) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    // Getters
    public String getDenominacion() {
        return denominacion;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }

    // Setters
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    // Método getDatos()
    /**
     * Retorna una cadena con la denominación y la unidad de medida del tipo de dato.
     * @return Una cadena formateada con los datos del tipo de dato.
     */
    public String getDatos() {
        return "Denominación: " + this.denominacion + ", Unidad de Medida: " + this.nombreUnidadMedida;
    }

}

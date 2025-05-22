package com.mycompany.esismicos;
public class TipoDeDato {

    // Atributos
    private String denominacion;
    private String nombreUnidadMedida;

    // Constructor
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

    // --- Método toString() (Añadido) ---
    @Override
    public String toString() {
        return "TipoDeDato{" +
               "denominacion='" + denominacion + '\'' +
               ", nombreUnidadMedida='" + nombreUnidadMedida + '\'' +
               '}';
    }
}

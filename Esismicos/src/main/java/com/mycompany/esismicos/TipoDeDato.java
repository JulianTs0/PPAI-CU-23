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

    /**
     * Devuelve la denominación y el nombreUnidadMedida de este tipo de dato,
     * siempre y cuando la denominación sea "velocidad de onda", "frecuencia de onda" o "longitud".
     *
     * @return Un array de Object[] que contiene {denominacion, nombreUnidadMedida}
     * si cumple con la condición, o null si no la cumple.
     */
    public Object[] getDatos() {
        System.out.println("TipoDeDato: Método getDatos() ejecutado para denominación: " + this.denominacion);

        // Aplicar el filtro según lo solicitado
        if (this.denominacion.equals("velocidad de onda") ||
                this.denominacion.equals("frecuencia de onda") ||
                this.denominacion.equals("longitud")) {
            System.out.println("TipoDeDato: Denominación '" + this.denominacion + "' cumple con el filtro. Retornando datos.");
            return new Object[]{this.denominacion, this.nombreUnidadMedida};
        } else {
            System.out.println("TipoDeDato: Denominación '" + this.denominacion + "' NO cumple con el filtro. Retornando null.");
            return null; // No cumple con las denominaciones requeridas
        }
    }
    
    //Metodo esTuDenominacion() 
    //retorna true si la denominacion es igual a la pasada por parametro
    public boolean esTuDenominacion(String denominaString){
        return (this.denominacion.equals(denominaString));
    }

    //Metodo esDenominacionDatos()
    //returna true si su denominacion es "Datos" 
    public boolean esDenominacionDatos() {
        return (esTuDenominacion("Datos"));
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

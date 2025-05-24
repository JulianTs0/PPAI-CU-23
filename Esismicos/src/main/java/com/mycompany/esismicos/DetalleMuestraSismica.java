package com.mycompany.esismicos;

public class DetalleMuestraSismica {

    // Atributos
    private double valor;
    // Asociación a TipoDeDato (UNO a UNO)
    private TipoDeDato tipoDeDato; 

    // Constructor
    // Ahora recibe también el TipoDeDato con el que se asocia
    public DetalleMuestraSismica(double valor, TipoDeDato tipoDeDato) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato; // Inicializa la asociación
    }

    // Getter
    public double getValor() {
        return this.valor;
    }

    // Setter (si es necesario que el valor pueda cambiar)
    public void setValor(double valor) {
        this.valor = valor;
    }

    // --- Getter para el TipoDeDato asociado ---
    public TipoDeDato getTipoDeDato() {
        return this.tipoDeDato;
    }

    // --- Setter para el TipoDeDato asociado (si es necesario que la asociación pueda cambiar) ---
    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }

    /**
     * Obtiene el valor del detalle. Luego, obtiene los datos de denominación y
     * nombreUnidadMedida del tipo de dato asociado, solo si la denominación
     * es "velocidad de onda", "frecuencia de onda" o "longitud".
     *
     * @return Un array de Object[] que contiene {valor, denominacion, nombreUnidadMedida}
     * si el tipo de dato cumple con el filtro, o null si no cumple.
     */
    public Object[] getDatos() {
        System.out.println("DetalleMuestraSismica: Método getDatos() ejecutado para valor: " + this.valor);

        if (this.tipoDeDato == null) {
            System.out.println("DetalleMuestraSismica: No hay TipoDeDato asociado. Retornando null.");
            return null;
        }

        // Obtener los datos del TipoDeDato, que internamente ya aplica el filtro
        // Esto devolverá {denominacion, nombreUnidadMedida} o null si no cumple el filtro.
        Object[] datosTipoDeDato = this.tipoDeDato.getDatos();

        if (datosTipoDeDato != null) {
            System.out.println("DetalleMuestraSismica: TipoDeDato '" + datosTipoDeDato[0] + "' cumple con el filtro.");
            // Si el tipo de dato cumple, combina el valor del detalle con los datos del tipo de dato
            Object[] resultado = new Object[3];
            resultado[0] = this.valor;             // Valor del DetalleMuestraSismica
            resultado[1] = datosTipoDeDato[0];     // Denominación del TipoDeDato
            resultado[2] = datosTipoDeDato[1];     // NombreUnidadMedida del TipoDeDato
            return resultado;
        } else {
            System.out.println("DetalleMuestraSismica: El TipoDeDato '" + this.tipoDeDato.getDenominacion() + "' no cumple el filtro. Retornando null.");
            return null; // El tipo de dato no es uno de los requeridos
        }
    }

    // --- Método toString() (Añadido) ---
    @Override
    public String toString() {
        return "DetalleMuestraSismica{" +
               "valor=" + valor +
               ", tipoDeDato=" + (tipoDeDato != null ? tipoDeDato.getNombreUnidadMedida() : "N/A") + // Muestra el nombre del TipoDeDato
               '}';
    }
}

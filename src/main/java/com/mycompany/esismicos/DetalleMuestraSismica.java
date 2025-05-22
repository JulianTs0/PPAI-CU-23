package com.mycompany.esismicos;
public class DetalleMuestraSismica {

    // Atributos
    private double valor;
    // --- Asociación a un solo elemento de TipoDeDato ---
    private TipoDeDato tipoDeDato; // Un DetalleMuestraSismica "tiene un" TipoDeDato
    // ----------------------------------------------------

    // Constructor
    // Ahora recibe también el TipoDeDato con el que se asocia
    public DetalleMuestraSismica(double valor, TipoDeDato tipoDeDato) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato; // Inicializa la asociación
    }

    // Getter
    public double getValor() {
        return valor;
    }

    // Setter (si es necesario que el valor pueda cambiar)
    public void setValor(double valor) {
        this.valor = valor;
    }

    // --- Getter para el TipoDeDato asociado ---
    public TipoDeDato getTipoDeDato() {
        return tipoDeDato;
    }

    // --- Setter para el TipoDeDato asociado (si es necesario que la asociación pueda cambiar) ---
    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }

    // Método getDatos()
    /**
     * Retorna una cadena con el valor del detalle de la muestra sísmica
     * y los datos del tipo de dato asociado.
     * @return Una cadena formateada con los datos del detalle y su tipo.
     */
    public String getDatos() {
        // Mejorado para incluir el tipo de dato, ya que la clase lo tiene.
        return "Valor: " + this.valor + (tipoDeDato != null ? " (Tipo: " + tipoDeDato.getNombreUnidadMedida() + ")" : "");
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

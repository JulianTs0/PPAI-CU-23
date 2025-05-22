package com.mycompany.esismicos;

import java.time.LocalDateTime; // Importa la clase LocalDateTime
import java.util.ArrayList;
import java.util.List;

public class MuestraSismica {

    // Atributos
    private LocalDateTime fechaHoraMuestra; // Cambiado a LocalDateTime

    // Composición: Relación de uno a muchos con DetalleMuestraSismica
    private List<DetalleMuestraSismica> detallesMuestra;

    // Constructor
    // Ahora el constructor recibe LocalDateTime
    public MuestraSismica(LocalDateTime fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detallesMuestra = new ArrayList<>(); // Inicializa la lista vacía al crear la MuestraSismica
    }

    // Getter para fechaHoraMuestra (tipo actualizado)
    public LocalDateTime getFechaHoraMuestra() {
        return this.fechaHoraMuestra;
    }

    // Setter para fechaHoraMuestra (tipo actualizado)
    public void setFechaHoraMuestra(LocalDateTime fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }

    // Métodos para manejar los DetalleMuestraSismica (composición)

    /**
     * Permite agregar un DetalleMuestraSismica a esta muestra.
     * @param detalle El objeto DetalleMuestraSismica a añadir.
     */
    public void addDetalle(DetalleMuestraSismica detalle) {
        if (detalle != null) {
            this.detallesMuestra.add(detalle);
        }
    }

    /**
     * Obtiene la lista de todos los detalles de datos de las muestras asociadas.
     * @return Una lista de objetos DetalleMuestraSismica.
     */
    public List<DetalleMuestraSismica> getDetalles() {
        // Devolvemos una copia defensiva para proteger la lista interna
        return new ArrayList<>(this.detallesMuestra);
    }

    /**
     * Crea un nuevo DetalleMuestraSismica y lo asocia a esta MuestraSismica.
     * @param valor El valor del detalle de la muestra.
     * @param tipoDeDato El tipo de dato asociado a este detalle.
     */
    public void crearDetalleMuestra(double valor, TipoDeDato tipoDeDato) {
        DetalleMuestraSismica nuevoDetalle = new DetalleMuestraSismica(valor, tipoDeDato);
        this.detallesMuestra.add(nuevoDetalle);
    }

    //devuelve un string con los los datos propios y los datos de cada detalleMuestra
    public String getDatos() {
        String datos = "MuestraSismica{ FechaHoraMuestra: " + this.fechaHoraMuestra + " "
            + "DetallesMuestraSismica: ";
        datos += "[";
        for (DetalleMuestraSismica detalle : this.detallesMuestra){
            datos += detalle.getDatos();
        }
        datos += "]}";
        return datos;
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "MuestraSismica{" +
               "fechaHoraMuestra=" + fechaHoraMuestra + // LocalDateTime se imprimirá de forma legible
               ", cantidadDetalles=" + detallesMuestra.size() +
               '}';
    }
}

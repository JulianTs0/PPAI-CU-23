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
    /**
     * Devuelve la fechaHoraMuestra de la muestra y, por cada detalle
     * filtrado por TipoDeDato (velocidad de onda, frecuencia de onda, longitud),
     * combina la fecha con los datos del detalle.
     *
     * @return Una lista de Object[] donde cada Object[] contiene
     * {fechaHoraMuestra, valorDetalle, denominacionTipoDeDato, nombreUnidadMedidaTipoDeDato}.
     */
    public List<Object[]> getDatos() {
        System.out.println("MuestraSismica: Método getDatos() ejecutado para muestra de fecha: " + this.fechaHoraMuestra);
        List<Object[]> datosMuestra = new ArrayList<>();

        // Ejecuta el método obtenerDetallesDatosMuestraSismica para obtener los detalles filtrados
        List<Object[]> detallesFiltrados = this.obtenerDetallesDatosMuestraSismica();

        if (detallesFiltrados != null && !detallesFiltrados.isEmpty()) {
            for (Object[] detalleData : detallesFiltrados) {
                // Crear un nuevo Object[] combinando la fecha de la muestra con los datos del detalle
                // Formato: {fechaHoraMuestra, valor, denominacion, nombreUnidadMedida}
                Object[] dataCompleta = new Object[4];
                dataCompleta[0] = this.fechaHoraMuestra; // Fecha y Hora de la Muestra
                dataCompleta[1] = detalleData[0];        // Valor del Detalle (viene de DetalleMuestraSismica.getDatos())
                dataCompleta[2] = detalleData[1];        // Denominación del TipoDeDato (viene de TipoDeDato.getDatos())
                dataCompleta[3] = detalleData[2];        // NombreUnidadMedida del TipoDeDato (viene de TipoDeDato.getDatos())
                datosMuestra.add(dataCompleta);
                System.out.println("MuestraSismica: Agregado dato completo para la fecha " + this.fechaHoraMuestra + " y tipo " + dataCompleta[2]);
            }
        } else {
            System.out.println("MuestraSismica: No se encontraron detalles relevantes o filtrados en esta muestra.");
        }
        System.out.println("MuestraSismica: Finalizada recolección de datos de la muestra. Total de entradas: " + datosMuestra.size());
        return datosMuestra;
    }

    /**
     * Itera por cada elemento que posee de DetalleMuestraSismica.
     * Por cada detalle, ejecuta el método getDatos() del detalle para
     * obtener el valor, la denominación y la unidad de medida, si cumplen el filtro
     * de tipo de dato.
     *
     * @return Una lista de Object[] donde cada Object[] contiene
     * {valor, denominacion, nombreUnidadMedida}.
     */
    public List<Object[]> obtenerDetallesDatosMuestraSismica() {
        System.out.println("MuestraSismica: Método obtenerDetallesDatosMuestraSismica() ejecutado.");
        List<Object[]> datosDetallesFiltrados = new ArrayList<>();

        if (this.detallesMuestra == null || this.detallesMuestra.isEmpty()) {
            System.out.println("MuestraSismica: No hay detalles de muestra asociados.");
            return datosDetallesFiltrados;
        }

        for (DetalleMuestraSismica detalle : this.detallesMuestra) {
            System.out.println("MuestraSismica: Procesando detalle de muestra con valor: " + detalle.getValor() + " (Tipo: " + (detalle.getTipoDeDato() != null ? detalle.getTipoDeDato().getDenominacion() : "N/A") + ")");
            // El método getDatos() de DetalleMuestraSismica devuelve {valor, denominacion, nombreUnidadMedida}
            // y ya aplica el filtro de tipo de dato internamente.
            Object[] dataDetalle = detalle.getDatos();
            if (dataDetalle != null) { // Si dataDetalle no es null, significa que el tipo de dato cumplió el filtro
                datosDetallesFiltrados.add(dataDetalle);
                System.out.println("MuestraSismica: Añadido detalle filtrado: Denominación=" + dataDetalle[1] + ", Valor=" + dataDetalle[0]);
            }
        }
        System.out.println("MuestraSismica: Finalizada recolección de detalles filtrados. Total de detalles: " + datosDetallesFiltrados.size());
        return datosDetallesFiltrados;
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

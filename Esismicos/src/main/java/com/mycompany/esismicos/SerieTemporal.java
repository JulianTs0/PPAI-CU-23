package com.mycompany.esismicos;

import java.time.LocalDateTime; // Importa la clase LocalDateTime
import java.util.ArrayList;
import java.util.List;

public class SerieTemporal {

    // Atributos de valor (no relacionales)
    private String condicionAlarma;
    private LocalDateTime fechaHoraIncioRegistroMuestras; // Corregido el nombre a 'Incio'
    private LocalDateTime fechaHoraRegistro;
    private double frecuenciaMuestreo;

    // --- Asociación 1:1 con la clase Estado ---
    private Estado estado;

    // --- Composición 1:M con MuestraSismica ---
    private List<MuestraSismica> muestrasSismicas;

    // Constructor con los atributos de valor y la asociación con Estado
    public SerieTemporal(String condicionAlarma, LocalDateTime fechaHoraIncioRegistroMuestras,
                         LocalDateTime fechaHoraRegistro, double frecuenciaMuestreo, Estado estado) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraIncioRegistroMuestras = fechaHoraIncioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.estado = estado; // Inicializa la asociación
        this.muestrasSismicas = new ArrayList<>(); // Inicializa la lista de muestras
    }

    // Getters y Setters de Atributos de Valor
    public String getCondicionAlarma() {
        return condicionAlarma;
    }

    public void setCondicionAlarma(String condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }

    public LocalDateTime getFechaHoraIncioRegistroMuestras() {
        return fechaHoraIncioRegistroMuestras;
    }

    public void setFechaHoraIncioRegistroMuestras(LocalDateTime fechaHoraIncioRegistroMuestras) {
        this.fechaHoraIncioRegistroMuestras = fechaHoraIncioRegistroMuestras;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public double getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }

    public void setFrecuenciaMuestreo(double frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }

    // --- Getter y Setter para la Asociación con Estado ---
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // --- Métodos para manejar la Composición con MuestraSismica ---

    /**
     * Obtiene la lista de todas las muestras sísmicas asociadas.
     * @return Una lista de objetos MuestraSismica (copia defensiva).
     */
    public List<MuestraSismica> getMuestrasSismicas() {
        return new ArrayList<>(this.muestrasSismicas);
    }

    /**
     * Añade una MuestraSismica a la serie temporal.
     * @param muestra La MuestraSismica a añadir.
     */
    public void addMuestraSismica(MuestraSismica muestra) {
        if (muestra != null) {
            this.muestrasSismicas.add(muestra);
        }
    }

    /**
     * Crea una nueva MuestraSismica y la asocia a esta SerieTemporal.
     * @param fechaHoraMuestra La fecha y hora de la muestra.
     */
    public void crearMuestraSismica(LocalDateTime fechaHoraMuestra) {
        MuestraSismica nuevaMuestra = new MuestraSismica(fechaHoraMuestra);
        this.muestrasSismicas.add(nuevaMuestra);
    }

    // --- Métodos solicitados (void y vacíos) ---

    public List<Object[]> getDatos() {
        System.out.println("SerieTemporal: Método getDatos() ejecutado para serie con número: " + this.fechaHoraRegistro);
        List<Object[]> datosSerie = new ArrayList<>();

        // Delega en obtenerDatosMuestraSismica para recolectar los datos de las muestras
        // Estos datos ya vienen con {fechaHoraMuestra, valor, denominacion, nombreUnidadMedida}
        List<Object[]> datosMuestras = this.obtenerDatosMuestraSismica();

        if (datosMuestras != null && !datosMuestras.isEmpty()) {
            datosSerie.addAll(datosMuestras);
            System.out.println("SerieTemporal: Añadidos " + datosMuestras.size() + " datos de las muestras sísmicas a la colección de la serie.");
        } else {
            System.out.println("SerieTemporal: No se obtuvieron datos de las muestras sísmicas para esta serie.");
        }
        System.out.println("SerieTemporal: Finalizada recolección de datos. Total de entradas: " + datosSerie.size());
        return datosSerie;
    }

    /**
     * Itera por cada elemento que posee de MuestraSismica.
     * Por cada muestra, ejecuta el método `getDatos()` de la muestra
     * para obtener la fechaHoraMuestra, valor, denominación y unidad de medida.
     *
     * @return Una lista de Object[] donde cada Object[] contiene
     * {fechaHoraMuestra, valor, denominacion, nombreUnidadMedida}.
     */
    public List<Object[]> obtenerDatosMuestraSismica() {
        System.out.println("SerieTemporal: Método obtenerDatosMuestraSismica() ejecutado.");
        List<Object[]> datosMuestras = new ArrayList<>();

        if (this.muestrasSismicas == null || this.muestrasSismicas.isEmpty()) {
            System.out.println("SerieTemporal: No hay muestras sísmicas asociadas a esta serie.");
            return datosMuestras;
        }

        for (MuestraSismica muestra : this.muestrasSismicas) {
            System.out.println("SerieTemporal: Procesando muestra sísmica de fecha: " + muestra.getFechaHoraMuestra());
            // El método getDatos() de MuestraSismica devuelve una lista de
            // {fechaHoraMuestra, valor, denominacion, nombreUnidadMedida}
            List<Object[]> datosDeUnaMuestra = muestra.getDatos();
            if (datosDeUnaMuestra != null && !datosDeUnaMuestra.isEmpty()) {
                datosMuestras.addAll(datosDeUnaMuestra);
                System.out.println("SerieTemporal: Añadidos " + datosDeUnaMuestra.size() + " datos de la muestra a la colección general.");
            }
        }

        // Una vez que se han obtenido todos los datos de las muestras,
        // se busca la estación sismológica y se añade su nombre.
        String nombreEstacion = this.buscarSismografo();
        if (nombreEstacion != null) {
            System.out.println("SerieTemporal: Nombre de la estación sismológica encontrada: " + nombreEstacion);
            // Iterar sobre los datos recolectados y añadir el nombre de la estación
            List<Object[]> datosCompletos = new ArrayList<>();
            for (Object[] datoExistente : datosMuestras) {
                // Crear un nuevo Object[] con un espacio adicional para el nombre de la estación
                Object[] nuevoDato = new Object[datoExistente.length + 1];
                System.arraycopy(datoExistente, 0, nuevoDato, 0, datoExistente.length);
                nuevoDato[datoExistente.length] = nombreEstacion; // Añadir el nombre de la estación al final
                datosCompletos.add(nuevoDato);
            }
            datosMuestras = datosCompletos; // Actualizar la lista con los datos extendidos
            System.out.println("SerieTemporal: Nombre de la estación '" + nombreEstacion + "' añadido a " + datosMuestras.size() + " registros.");
        } else {
            System.out.println("SerieTemporal: No se pudo encontrar el nombre de la estación para esta serie temporal.");
        }

        System.out.println("SerieTemporal: Finalizada recolección de datos de muestras. Total de datos: " + datosMuestras.size());
        return datosMuestras;
    }

    /**
     * Itera sobre todos los sismógrafos en la DataBase
     * y llama a `soyTuSerieTemporal()` para encontrar el nombre de la estación
     * sismológica a la que pertenece el sismógrafo asociado a esta serie temporal.
     *
     * @return El nombre de la estación sismológica o `null` si no se encuentra.
     */
    public String buscarSismografo() {
        System.out.println("SerieTemporal: Método buscarSismografo() ejecutado.");
        for (Sismografo sismografo : DataBase.sismografos) { // Asumo que existe una lista 'sismografos' en DataBase
            System.out.println("SerieTemporal: Preguntando a Sismografo " + sismografo.getIdentificadorSismografo() + " si es su serie.");
            String nombreEstacion = sismografo.soyTuSerieTemporal(this);
            if (nombreEstacion != null) {
                System.out.println("SerieTemporal: Sismografo " + sismografo.getIdentificadorSismografo() + " confirmó la serie. Estación: " + nombreEstacion);
                return nombreEstacion;
            }
        }
        System.out.println("SerieTemporal: No se encontró ningún sismógrafo asociado a esta serie temporal.");
        return null; // Si no se encuentra ningún sismógrafo asociado
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return "SerieTemporal{" +
               "condicionAlarma='" + condicionAlarma + '\'' +
               ", fechaHoraIncioRegistroMuestras=" + fechaHoraIncioRegistroMuestras +
               ", fechaHoraRegistro=" + fechaHoraRegistro +
               ", frecuenciaMuestreo=" + frecuenciaMuestreo +
               ", estado=" + (estado != null ? estado.getNombreEstado() : "N/A") + // Muestra el nombre del estado
               ", numMuestrasSismicas=" + (muestrasSismicas != null ? muestrasSismicas.size() : 0) +
               '}';
    }
}

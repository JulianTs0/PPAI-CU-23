package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.utils.DataBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SerieTemporal {
    
    //ATRIBUTOS
    private String condicionAlarma;
    private LocalDateTime fechaHoraIncioRegistroMuestras;
    private LocalDateTime fechaHoraRegistro;
    private float frecuenciaMuestreo;
    //ASOCIACION
    private Estado estado;
    //AGREGACION
    private List<MuestraSismica> muestraSismicas;
    // DEPENDENCIA
    private Sismografo seleccionadoSismografo;
    
    //CONSTRUCTOR

    public SerieTemporal(String condicionAlarma, 
            LocalDateTime fechaHoraIncioRegistroMuestras,
            LocalDateTime fechaHoraRegistro, 
            float frecuenciaMuestreo, 
            Estado estadoActual, 
            List<MuestraSismica> muestraSismicas) {
        this.condicionAlarma = condicionAlarma;
        this.fechaHoraIncioRegistroMuestras = fechaHoraIncioRegistroMuestras;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.frecuenciaMuestreo = frecuenciaMuestreo;
        this.estado = estadoActual;
        this.muestraSismicas = muestraSismicas;
    }

    public SerieTemporal(List<MuestraSismica> muestraSismicas) {
        this.muestraSismicas = muestraSismicas;
    }

    // METODOS DOMINIO
    
    public String[][] obtenerDatosMuestraSismica(){
        
        // crear y manipular la List
        List<String[]> datosPorMuestraSismica = new ArrayList<String[]>();
        // LOOP Muestra Sismica
        // [Mientras haya Muestras Sismicas]
        for (MuestraSismica muestra : this.muestraSismicas) {
            // por cada MuestraSismica apendea las n filas que tenga cada muestra
            // las n filas obtenidas de cada obtenerDatosDetallesMuestras()
            datosPorMuestraSismica.add(muestra.obtenerDatosDetallesMuestras()); 
        }
        // esto devuelvio String[n][5]
        
        // monbre EstacionSismologica
        String nombreEstacionSismologica = obtenerNombreEstacionSismologica();
        
        // asignar a la 5 columnna (nombreEstacion) en cada fila
        for (String[] fila : datosPorMuestraSismica) {
            fila[4] = nombreEstacionSismologica;
        }
        
        return datosPorMuestraSismica.toArray(new String[0][]);
    }
    
    public String obtenerNombreEstacionSismologica(){
        // buscar SISMOGRAFO
        this.seleccionadoSismografo = this.buscarSismografo();
        
        // pedir a SISMOGRAFO nombreEstacion
        // y retornar nombre
        return this.seleccionadoSismografo.obtenerNombreEstacionSismologica();
    }
    
    // buscar entre todos los Sismografos con *soyTuSerieTemporal()
    public Sismografo buscarSismografo(){
        Sismografo[] todosSismografos = DataBase.cargarSismografos();
        for (Sismografo elemSismografo : todosSismografos) {
            if (elemSismografo.soyTuSerieTemporal(this)) {
                return elemSismografo;
            }
        }
        return null;
    }
    
    public String getDatos(){
        return "" + this.fechaHoraIncioRegistroMuestras + "," + this.fechaHoraRegistro + "," + this.frecuenciaMuestreo;
    }
    
}

package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.refactor.Estado;
import com.mycompany.ppai_cu_23.persistance.DataBaseService;
import jakarta.persistence.*;
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
@Entity
@Table(name = "series_temporales")
public class SerieTemporal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ATRIBUTOS

    @Column(name = "condicion_alarma")
    private String condicionAlarma;

    @Column(name = "fecha_hora_inicio_registro")
    private LocalDateTime fechaHoraIncioRegistroMuestras;

    @Column(name = "fecha_hora_registro")
    private LocalDateTime fechaHoraRegistro;

    @Column(name = "frecuencia_muestreo")
    private float frecuenciaMuestreo;

    //ASOCIACION

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id")
    private Estado estado;

    //AGREGACION

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "serie_temporal_id") // FK en 'muestras_sismicas'
    private List<MuestraSismica> muestraSismicas;

    // DEPENDENCIA

    @Transient // Se usa para la lógica de búsqueda, no es una columna de BBDD
    private Sismografo seleccionadoSismografo;

    // METODOS DOMINIO
    
    public String[][] obtenerDatosMuestraSismica(){
        
        // crear y manipular la List
        List<String[]> datosPorMuestraSismica = new ArrayList<String[]>();
        // LOOP Muestra Sismica
        // [Mientras haya Muestras Sismicas]
        for (MuestraSismica muestra : this.muestraSismicas) {
            // por cada MuestraSismica apendea las n filas que tenga cada muestra
            // las n filas obtenidas de cada obtenerDatosDetallesMuestras()
            List<String[]> datosDetalle = muestra.obtenerDatosDetallesMuestras();
            datosPorMuestraSismica.addAll(datosDetalle);
        }
        // esto devuelvio String[n][4]
        
        // monbre EstacionSismologica
        String nombreEstacionSismologica = this.obtenerNombreEstacionSismologica();
        
        // asignar a la 4 columnna (nombreEstacion) en cada fila
        for (String[] fila : datosPorMuestraSismica) {
            fila[3] = nombreEstacionSismologica;
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
        Sismografo[] todosSismografos = DataBaseService.getSismografos();
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

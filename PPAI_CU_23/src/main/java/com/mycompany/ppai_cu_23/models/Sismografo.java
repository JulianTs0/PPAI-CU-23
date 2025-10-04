package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sismografo {

    //ATRIBUTO 
    private String identificadorSismografo;
    private String nroSerie;
    private LocalDateTime fechaAdquisicon;
    //ASOCIACION
    private EstacionSismologica estacionSismologica;
    //COMPOSICION 1..*
    private List<SerieTemporal> serieTemporals;
    private List<CambioDeEstado> cambioDeEstados;

    //CONSTRUCTOR

    public Sismografo(String identificadorSismografo, 
            EstacionSismologica estacionSismologica, 
            List<SerieTemporal> serieTemporals, 
            List<CambioDeEstado> cambioDeEstados) {
        this.identificadorSismografo = identificadorSismografo;
        this.estacionSismologica = estacionSismologica;
        this.serieTemporals = serieTemporals;
        this.cambioDeEstados = cambioDeEstados;
    }
    
    // constructor usado en DATA-BASE
    public Sismografo(EstacionSismologica estacionSismologica, List<SerieTemporal> serieTemporals) {
        this.estacionSismologica = estacionSismologica;
        this.serieTemporals = serieTemporals;
    }
    
    public boolean soyTuSerieTemporal(SerieTemporal serieTemporal){
        for (SerieTemporal elemSerie : this.serieTemporals) {
            if (elemSerie == serieTemporal) {
                return true;
            }
        }
        return false;
    }
    
    public String obtenerNombreEstacionSismologica(){
        return this.estacionSismologica.getNombre();
    }
}

package com.mycompany.ppai_cu_23.models;

import jakarta.persistence.*;
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
@Entity
@Table(name = "sismografos")
public class Sismografo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ATRIBUTO

    @Column(name = "identificador_sismografo", unique = true)
    private String identificadorSismografo;

    @Column(name = "nro_serie")
    private String nroSerie;

    @Column(name = "fecha_adquisicion")
    private LocalDateTime fechaAdquisicon;

    //ASOCIACION

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacion_sismologica_id")
    private EstacionSismologica estacionSismologica;

    //COMPOSICION 1..*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "sismografo_id")
    private List<SerieTemporal> serieTemporals;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "sismografo_id")
    private List<CambioDeEstado> cambioDeEstados;

    // METODOS DOMINIO
    
    public boolean soyTuSerieTemporal(SerieTemporal serieTemporal){
        for (SerieTemporal elemSerie : this.serieTemporals) {
            if (elemSerie.getId().equals(serieTemporal.getId())) {
                return true;
            }
        }
        return false;
    }
    
    public String obtenerNombreEstacionSismologica(){
        return this.estacionSismologica.getNombre();
    }
}

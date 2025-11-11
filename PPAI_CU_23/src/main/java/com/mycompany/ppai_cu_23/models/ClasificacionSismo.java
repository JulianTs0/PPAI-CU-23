package com.mycompany.ppai_cu_23.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clasificaciones_sismo")
public class ClasificacionSismo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ATRIBUTO

    @Column(nullable = false)
    private String nombre;

    @Column(name = "km_profundidad_desde")
    private float kmProfundidadDesde;

    @Column(name = "km_profundidad_hasta")
    private float kmProfundidadHasta;

}

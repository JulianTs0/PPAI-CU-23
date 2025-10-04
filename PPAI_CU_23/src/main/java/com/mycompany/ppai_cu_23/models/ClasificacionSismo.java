package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClasificacionSismo {

    //ATRIBUTO
    private String nombre;
    private float kmProfundidadDesde;
    private float kmProfundidadHasta;

    //CONSTRUCTOR

    public ClasificacionSismo(String nombre) {
        this.nombre = nombre;
    }

}

package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrigenDeGeneracion {
    
    //ATRIBUTO
    private String nombre;
    private String descripcion;

    //CONSTRUCTOR

    public OrigenDeGeneracion(String nombre) {
        this.nombre = nombre;
    }

}

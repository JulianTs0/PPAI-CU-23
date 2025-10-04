package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    //ATRIBUTO
    // el CU no usa ATRIBUTOS
    private String nombre;
    private String apellido;
    private String mail;
    private String telefono;
    
    // CONSTRUCTOR
    
    public Empleado(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }


}

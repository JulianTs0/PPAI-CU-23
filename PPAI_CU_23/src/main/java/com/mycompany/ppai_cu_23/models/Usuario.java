package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    // ATRIBUTO
    private String nombreUsuario;
    private String contraseña;
    // ASOCIACION
    private Empleado empleado;


}

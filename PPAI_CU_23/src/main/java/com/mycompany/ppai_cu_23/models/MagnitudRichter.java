package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MagnitudRichter {
    
    //ATRIBUTO
    int numero;
    String descripcionMagnitud;

    //CONSTRUCTOR

    public MagnitudRichter(int numero) {
        this.numero = numero;
    }

}

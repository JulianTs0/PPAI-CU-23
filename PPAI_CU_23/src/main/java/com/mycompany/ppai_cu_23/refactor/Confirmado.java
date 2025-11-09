package com.mycompany.ppai_cu_23.refactor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Confirmado extends Estado {

    public Confirmado(String ambito, String nombre) {
        super(ambito, nombre);
    }

}

package com.mycompany.ppai_cu_23.refactor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Anulado extends Estado {

    public Anulado(String ambito, String nombre) {
        super(ambito, nombre);
    }

}

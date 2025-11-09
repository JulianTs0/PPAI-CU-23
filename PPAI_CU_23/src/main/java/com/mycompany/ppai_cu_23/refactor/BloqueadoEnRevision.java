package com.mycompany.ppai_cu_23.refactor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BloqueadoEnRevision extends Estado {

    public BloqueadoEnRevision(String ambito, String nombre) {
        super(ambito, nombre);
    }

}

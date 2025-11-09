package com.mycompany.ppai_cu_23.refactor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Normal extends Estado{

    public Normal(String ambito, String nombre) {
        super(ambito, nombre);
    }

}

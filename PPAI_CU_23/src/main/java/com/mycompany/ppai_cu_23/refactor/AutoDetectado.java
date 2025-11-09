package com.mycompany.ppai_cu_23.refactor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AutoDetectado extends Estado {

    public AutoDetectado(String ambito, String nombre) {
        super(ambito, nombre);
    }
}

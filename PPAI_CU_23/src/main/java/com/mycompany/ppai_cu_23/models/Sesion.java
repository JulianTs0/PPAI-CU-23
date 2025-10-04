package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sesion {
    
    // ATRIBUTO
    private LocalDateTime fechaHoraLogeo;
    private LocalDateTime fechaHoraDeslogeo;
    // ASOCIACION
    private Usuario usuarioLogeado;

}

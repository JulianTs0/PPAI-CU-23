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
public class CambioDeEstado {

    //ATRIBUTO
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    //ASOCIACION
    private Estado estado;
    private Empleado empleado;

    // METODOS DOMINIO
    
    public boolean esEstadoActual(){
        return(this.fechaHoraFin == null);
    }

}

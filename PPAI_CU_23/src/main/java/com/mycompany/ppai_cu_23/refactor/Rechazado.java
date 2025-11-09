package com.mycompany.ppai_cu_23.refactor;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.models.EventoSismico;
import com.mycompany.ppai_cu_23.models.Usuario;
import com.mycompany.ppai_cu_23.utils.DataBase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Rechazado extends Estado {

    public Rechazado(String ambito, String nombre) {
        super(ambito, nombre);
    }

    @Override
    public void revisar(Usuario usuario, LocalDateTime fechaHoraActual, EventoSismico evento, List<CambioDeEstado> cambiosDeEstado){
        System.out.println("Transicion no valida");
    }

}

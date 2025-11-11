package com.mycompany.ppai_cu_23.refactor;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.models.EventoSismico;
import com.mycompany.ppai_cu_23.models.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estado_rechazado")
public class Rechazado extends Estado {

    public Rechazado(String ambito, String nombre) {
        super(ambito, nombre);
    }

    @Override
    public void revisar(Usuario usuario, LocalDateTime fechaHoraActual, EventoSismico evento, List<CambioDeEstado> cambiosDeEstado){
        System.out.println("Transicion no valida");
    }

}

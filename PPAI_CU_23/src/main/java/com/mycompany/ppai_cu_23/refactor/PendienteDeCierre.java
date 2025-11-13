package com.mycompany.ppai_cu_23.refactor;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.models.EventoSismico;
import com.mycompany.ppai_cu_23.models.Usuario;
import jakarta.persistence.DiscriminatorValue;
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
@Table(name = "estado_pendiente_de_cierre")
public class PendienteDeCierre extends Estado {

    public PendienteDeCierre(String ambito, String nombre) {
        super(ambito, nombre);
    }

    @Override
    public void revisar(Usuario usuario, LocalDateTime fechaHoraActual, EventoSismico evento, List<CambioDeEstado> cambiosDeEstado){
        return;
    }

    @Override
    public void rechazar(Usuario usuario, LocalDateTime fechaHoraActual, EventoSismico evento, List<CambioDeEstado> cambiosDeEstado){
        return;
    }

}

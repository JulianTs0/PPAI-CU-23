package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.refactor.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cambios_de_estado")
public class CambioDeEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ATRIBUTO

    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    //ASOCIACION

    @ManyToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    // METODOS DOMINIO
    
    public boolean esEstadoActual(){
        return(this.fechaHoraFin == null);
    }

}

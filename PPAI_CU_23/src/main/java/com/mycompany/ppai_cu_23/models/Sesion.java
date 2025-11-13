package com.mycompany.ppai_cu_23.models;

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
@Table(name = "sesiones")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ATRIBUTO

    @Column(name = "fecha_hora_logeo", nullable = false)
    private LocalDateTime fechaHoraLogeo;

    @Column(name = "fecha_hora_deslogeo")
    private LocalDateTime fechaHoraDeslogeo;

    // ASOCIACION

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioLogeado;
}

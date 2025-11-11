package com.mycompany.ppai_cu_23.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", unique = true, nullable = false, length = 50)
    private String nombreUsuario;

    @Column(nullable = false)
    private String contraseña;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado empleado;

}

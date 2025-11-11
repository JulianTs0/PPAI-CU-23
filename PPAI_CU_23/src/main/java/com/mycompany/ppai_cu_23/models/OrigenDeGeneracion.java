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
@Table(name = "origenes_de_generacion")
public class OrigenDeGeneracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ATRIBUTO

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000) // Ejemplo de cómo limitar el tamaño
    private String descripcion;

}

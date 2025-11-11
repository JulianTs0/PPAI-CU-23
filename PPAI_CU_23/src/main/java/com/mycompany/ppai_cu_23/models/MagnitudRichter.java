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
@Table(name = "magnitudes_richter")
public class MagnitudRichter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ATRIBUTO

    @Column(nullable = false)
    int numero;

    @Column(name = "descripcion_magnitud")
    String descripcionMagnitud;

}

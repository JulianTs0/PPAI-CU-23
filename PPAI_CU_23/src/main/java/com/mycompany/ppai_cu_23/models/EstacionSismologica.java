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
@Table(name = "estaciones_sismologicas")
public class EstacionSismologica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "documento_certificacion")
    private String documentoCertificacionAdquirido;

    @Column(name = "codigo_estacion", unique = true)
    private String codigoEstacion;

    @Column(name = "fecha_solicitud_certificacion")
    private LocalDateTime fechaSolicitudCertificacion;

    private double latitud;

    private double longitud;

    @Column(name = "nro_certificacion")
    private String nroCertificacionAdquisicion;

    @Column(nullable = false)
    private String nombre;

}

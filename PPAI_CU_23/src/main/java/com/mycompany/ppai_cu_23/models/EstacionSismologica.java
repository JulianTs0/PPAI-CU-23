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
public class EstacionSismologica {

    private String documentoCertificacionAdquirido;
    private String codigoEstacion;
    private LocalDateTime fechaSolicitudCertificacion; 
    private double latitud;
    private double longitud;
    private String nroCertificacionAdquisicion;
    private String nombre; //NO SE USA NINGUNA VARIEBLE EN EL CU
    
    //CONSTRUCTOR
    
    public EstacionSismologica(String nombre) {
        this.nombre = nombre;
    }


}

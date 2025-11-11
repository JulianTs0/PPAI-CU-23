package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.persistance.DataBaseService;
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
@Table(name = "tipos_de_dato")
public class TipoDeDato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String denominacion;

    @Column(name = "nombre_unidad_medida")
    private String nombreUnidadMedida;

    @Column(name = "valor_umbral")
    private float valorUmbral;

    // METODOS DOMINIO

    public boolean esDenominacionVelocidadOnda(){
        return this.denominacion.equals(DataBaseService.nombresDenominacion.Velocidad_De_Onda.name());
    }
    
    public boolean esDenominacionFrecuenciaOnda(){
        return this.denominacion.equals(DataBaseService.nombresDenominacion.Frecuencia_De_Onda.name());
    }
    
    public boolean esDenominacionLongitudOnda(){
        return this.denominacion.equals(DataBaseService.nombresDenominacion.Longitud_De_Onda.name());
    }
    
}

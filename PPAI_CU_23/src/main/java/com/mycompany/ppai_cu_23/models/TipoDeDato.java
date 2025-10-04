package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.utils.DataBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoDeDato {
    
    private String denominacion;
    private String nombreUnidadMedida;
    private float valorUmbral;
    
    //CONSTRUCTOR

    public TipoDeDato(String denominacion, String nombreUnidadMedida) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    // METODOS DOMINIO

    public boolean esDenominacionVelocidadOnda(){
        return this.denominacion.equals(DataBase.nombresDenominacion.Velocidad_De_Onda.name());
    }
    
    public boolean esDenominacionFrecuenciaOnda(){
        return this.denominacion.equals(DataBase.nombresDenominacion.Frecuencia_De_Onda.name());
    }
    
    public boolean esDenominacionLongitudOnda(){
        return this.denominacion.equals(DataBase.nombresDenominacion.Longitud_De_Onda.name());
    }
    
}

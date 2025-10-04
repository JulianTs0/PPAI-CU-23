package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMuestraSismica {
    
    private float valor;
    private TipoDeDato tipoDeDato;
    
    // METODOS DOMINIO
    
    // comprara denominaciones
    public boolean esDenominacionVelocidadOnda(){
        return this.tipoDeDato.esDenominacionVelocidadOnda();
    }
    
    public boolean esDenominacionFrecuenciaOnda(){
        return this.tipoDeDato.esDenominacionFrecuenciaOnda();
    }
    
    public boolean esDenominacionLongitudOnda(){
        return this.tipoDeDato.esDenominacionLongitudOnda();
    }
    
    public String getDatos(){
        return "" + this.valor + "," + this.tipoDeDato;
    }
}

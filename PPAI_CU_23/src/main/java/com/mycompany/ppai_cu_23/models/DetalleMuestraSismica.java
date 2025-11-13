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
@Table(name = "detalles_muestra_sismica")
public class DetalleMuestraSismica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private float valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_de_dato_id", nullable = false)
    private TipoDeDato tipoDeDato;
    
    // METODOS DOMINIO
    

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

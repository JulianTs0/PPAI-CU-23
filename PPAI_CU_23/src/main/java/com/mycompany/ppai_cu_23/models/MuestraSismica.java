package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MuestraSismica {
    
    // ATRIBUTO
    private LocalDateTime fechaHoraMuestra;
    //COMPOSICION
    private List<DetalleMuestraSismica> detalleMuestraSismicas;

    // SETTERS

    public void crearDetalleMuestra(float valor, TipoDeDato tipoDeDato){
        this.detalleMuestraSismicas.add(new DetalleMuestraSismica(valor, tipoDeDato));
    }
    
    // METODOS DOMINIO

    // devuelve String[n][5]
    public String[] obtenerDatosDetallesMuestras() {
        // LOOP 1 velocidad:Detalle
        DetalleMuestraSismica velocidadDetalle = this.buscarDetalleVelocidadOnda();
        String velocidadValor = "" + velocidadDetalle.getValor();
        // LOOP 2 frecuencia:Detalle
        DetalleMuestraSismica frecuenciaDetalle = this.buscarDetalleFrecuenciaOnda();
        String frecuenciaValor = "" + frecuenciaDetalle.getValor();
        // LOOP 3 longutud:Detalle
        DetalleMuestraSismica longutudDetalle = this.buscarDetalleLongitudOnda();
        String longitudValor = "" + longutudDetalle.getValor();
        // geFechaHoraMuestra()
        String[] res = {
            "" + this.getFechaHoraMuestra(),
            velocidadValor,
            frecuenciaValor,
            longitudValor,
            ""};
        return res;
    }
    
    // LOOP 1 velocidad:Detalle
    public DetalleMuestraSismica buscarDetalleVelocidadOnda(){
        for (DetalleMuestraSismica detalle : this.detalleMuestraSismicas) {
            if (detalle.esDenominacionVelocidadOnda()) {
                return detalle;
            }
        }
        return null;
    }
    
    // LOOP 2 frecuencia:Detalle
    public DetalleMuestraSismica buscarDetalleFrecuenciaOnda(){
        for (DetalleMuestraSismica detalle : this.detalleMuestraSismicas) {
            if (detalle.esDenominacionFrecuenciaOnda()) {
                return detalle;
            }
        }
        return null;
    }
    
    // LOOP 3 longutud:Detalle
    public DetalleMuestraSismica buscarDetalleLongitudOnda(){
        for (DetalleMuestraSismica detalle : this.detalleMuestraSismicas) {
            if (detalle.esDenominacionLongitudOnda()) {
                return detalle;
            }
        }
        return null;
    }
    
    public String getDatos(){
        return "" + this.fechaHoraMuestra;
    }
    
}

package com.mycompany.ppai_cu_23.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<String[]> obtenerDatosDetallesMuestras() {

        List<String[]> datosDetalleMuestra = new ArrayList<>();

        List<DetalleMuestraSismica> detalles = this.buscarDetallesMuestra();

        for(DetalleMuestraSismica detalle : detalles){
            String[] res = {
                "" + this.getFechaHoraMuestra(),
                "" + detalle.getTipoDeDato().getDenominacion(),
                "" + detalle.getValor(),
                ""};
            datosDetalleMuestra.add(res);
        }

        return datosDetalleMuestra;
    }

    public List<DetalleMuestraSismica> buscarDetallesMuestra(){

        List<DetalleMuestraSismica> detalles = new ArrayList<>();

        for (DetalleMuestraSismica detalle : this.detalleMuestraSismicas) {
            if (detalle.esDenominacionVelocidadOnda() ||
                detalle.esDenominacionFrecuenciaOnda() ||
                detalle.esDenominacionLongitudOnda()
            ) {
                detalles.add(detalle);
            }
        }

        return detalles;
    }
    
    public String getDatos(){
        return "" + this.fechaHoraMuestra;
    }
    
}

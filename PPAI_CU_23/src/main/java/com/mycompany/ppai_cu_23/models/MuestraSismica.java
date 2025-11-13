package com.mycompany.ppai_cu_23.models;

import jakarta.persistence.*;
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
@Entity
@Table(name = "muestras_sismicas")
public class MuestraSismica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ATRIBUTO

    @Column(name = "fecha_hora_muestra", nullable = false)
    private LocalDateTime fechaHoraMuestra;

    // COMPOSICION

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "muestra_sismica_id")
    private List<DetalleMuestraSismica> detalleMuestraSismicas;
    
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

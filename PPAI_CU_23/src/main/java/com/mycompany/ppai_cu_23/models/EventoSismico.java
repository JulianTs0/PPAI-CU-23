package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.refactor.Estado;
import com.mycompany.ppai_cu_23.utils.Debugger;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventos_sismicos")
public class EventoSismico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ATRIBUTO

    @Column(name = "fecha_hora_ocurrencia", nullable = false)
    private LocalDateTime fechaHoraOcurrencia;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(name = "latitud_hipocentro")
    private float latitudHipocentro;

    @Column(name = "longitud_hipocentro")
    private float longitudHipocentro;

    @Column(name = "latitud_epicentro")
    private float latitudEpicentro;

    @Column(name = "longitud_epicentro")
    private float longitudEpicentro;

    @Column(name = "valor_magnitud")
    private float valorMagnitud;

    //ASOCIACION

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clasificacion_sismo_id")
    private ClasificacionSismo clasificacionSismo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origen_de_generacion_id")
    private OrigenDeGeneracion origenDeGeneracion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alcance_sismo_id")
    private AlcanceSismo alcanceSismo;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "estado_id")
    private Estado estadoActual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "magnitud_richter_id")
    private MagnitudRichter magnitud;

    //AGREGACION

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_sismico_id")
    private List<CambioDeEstado> cambioDeEstados;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(
        name = "evento_sismico_series",
        joinColumns = @JoinColumn(name = "evento_sismico_id"),
        inverseJoinColumns = @JoinColumn(name = "serie_temporal_id")
    )
    private List<SerieTemporal> serieTemporals;

    // METODOS DOMINIO

    public boolean esAutoDetectado(){
        return this.estadoActual.esAutoDetectado();
    }

    public boolean esPendienteRevision(){
        return this.estadoActual.esPendienteDeRevision();
    }

    public void revisar(Usuario usuario, LocalDateTime fechaHoraActual){
        
        // DEBUGGER  antes
        Debugger.ptrintCambiosDeEstadoDeEvento(this);

        this.estadoActual.revisar(usuario, fechaHoraActual, this , this.cambioDeEstados);

        // DEBUGGER  despues
        Debugger.ptrintCambiosDeEstadoDeEvento(this);
    }
    
    public void rechazar(Usuario usuario, LocalDateTime fechaHoraActual){
        
        // DEBUGGER  antes
        Debugger.ptrintCambiosDeEstadoDeEvento(this);

        this.estadoActual.rechazar(usuario, fechaHoraActual, this, this.cambioDeEstados);

        // DEBUGGER  despues
        Debugger.ptrintCambiosDeEstadoDeEvento(this);
    }

    public String[] obtenerDatosEventoSismico(){
        String[] datosSismicos = {
                this.alcanceSismo.getNombre(),
                this.clasificacionSismo.getNombre(),
                this.origenDeGeneracion.getNombre(),
                "" + this.magnitud.getNumero()
        };
        return datosSismicos;
    }

    public String[][] obtenerDatosSerieTemporal(){
        
        // va a juntar las n= filas 
        // de todas las series temporales que tenga
        List<String[]> datosPorSerieTemporal = new ArrayList<String[]>();
        
        // LOOP Serie temporal
        //[Mientras haya Serie temporales asociadas al EventoSismico]
        for (SerieTemporal serie : this.serieTemporals) {
            // por cada serieTemporal apendea las n filas que tenga cada serie
            // las n filas obtenidas de cada obtenerDatosMuestraSismica()
            datosPorSerieTemporal.addAll(Arrays.asList(serie.obtenerDatosMuestraSismica())); 
        }
        // esto devuelvio String[n][4]
        
        return datosPorSerieTemporal.toArray(new String[0][]);
    }
    
    public String obtenerUbicacionEpicentro(){
        return (this.latitudEpicentro + " : " + this.longitudEpicentro);
    }
    
    public String obtenerUbicacionHipocentro(){
        return (this.latitudHipocentro + " : " + this.longitudHipocentro);
    }

    public CambioDeEstado buscarCambioDeEstadoActual(){
        for (CambioDeEstado cambio : this.cambioDeEstados) {
            if (cambio.esEstadoActual()) {
                return cambio;
            }
        }
        return null;
    }

    public void adquirirDatos(){
        System.out.println("Metodo de delegacion no implementado");
    }

    public void cerrar(){
        System.out.println("Metodo de delegacion no implementado");
    }

    public void confirmar(){
        System.out.println("Metodo de delegacion no implementado");
    }

    public void derivar(){
        System.out.println("Metodo de delegacion no implementado");
    }

    public void controlarTiempo(){
        System.out.println("Metodo de delegacion no implementado");
    }

    public void anular(){
        System.out.println("Metodo de delegacion no implementado");
    }
}

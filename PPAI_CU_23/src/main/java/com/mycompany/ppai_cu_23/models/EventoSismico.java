package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.utils.Debugger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoSismico {
    
    //ATRIBUTO
    private LocalDateTime fechaHoraOcurrencia;
    private LocalDateTime fechaHoraFin;
    private float latitudHipocentro;
    private float longitudHipocentro;
    private float latitudEpicentro;
    private float longitudEpicentro;
    private float valorMagnitud;
    //ASOCIACION
    private ClasificacionSismo clasificacionSismo;
    private OrigenDeGeneracion origenDeGeneracion;
    private AlcanceSismo alcanceSismo;
    private Estado estadoActual;
    private MagnitudRichter magnitud;
    //AGREGACION
    private List<CambioDeEstado> cambioDeEstados;
    private List<SerieTemporal> serieTemporals;

    //CONSTRUCTOR (pendiente)

    public EventoSismico(
            LocalDateTime fechaHoraOcurrencia,
            float latitudHipocentro, 
            float longitudHipocentro, 
            float latitudEpicentro, 
            float longitudEpicentro,
            float valorMagnitud,
            ClasificacionSismo clasificacionSismo, 
            OrigenDeGeneracion origenDeGeneracion, 
            AlcanceSismo alcanceSismo, 
            Estado estadoActual, 
            MagnitudRichter magnitud, 
            List<CambioDeEstado> cambioDeEstados, 
            List<SerieTemporal> serieTemporals) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudHipocentro = latitudHipocentro;
        this.longitudHipocentro = longitudHipocentro;
        this.latitudEpicentro = latitudEpicentro;
        this.longitudEpicentro = longitudEpicentro;
        this.valorMagnitud = valorMagnitud;
        this.clasificacionSismo = clasificacionSismo;
        this.origenDeGeneracion = origenDeGeneracion;
        this.alcanceSismo = alcanceSismo;
        this.estadoActual = estadoActual;
        this.magnitud = magnitud;
        this.cambioDeEstados = cambioDeEstados;
        this.serieTemporals = serieTemporals;
    }

    // METODOS DOMINIO

    public boolean esAutoDetectado(){
        return this.estadoActual.esAutoDetectado();
    }

    public boolean esPendienteRevision(){
        return this.estadoActual.esPendienteDeRevision();
    }

    public void bloquearEnRevision(Estado bloqueadoEnRevision, Usuario usuario, LocalDateTime fechaHoraActual){
        
        // DEBUGGER  antes
        Debugger.ptrintCambiosDeEstadoDeEvento(this);
        
        // buscar CAMBIO-ACTUAL
        CambioDeEstado cambioDeEstadoActual = buscarCambioDeEstadoActual();
        // fechahorafin al CAMBIO-ACTUAL
        cambioDeEstadoActual.setFechaHoraFin(fechaHoraActual);
        // crear CAMBIO-NUEVO
        this.crearCamioDeEstado(bloqueadoEnRevision, usuario, fechaHoraActual);
        // actualizar estado actual
        this.setEstadoActual(bloqueadoEnRevision);
        
        // DEBUGGER  despues
        Debugger.ptrintCambiosDeEstadoDeEvento(this);
    }
    
    public void rechazar(Estado rechazado, Usuario usuario, LocalDateTime fechaHoraActual){
        
        // DEBUGGER  antes
        Debugger.ptrintCambiosDeEstadoDeEvento(this);
        
        // buscar CAMBIO-ACTUAL
        CambioDeEstado cambioDeEstadoActual = buscarCambioDeEstadoActual();
        // fechahorafin al CAMBIO-ACTUAL
        cambioDeEstadoActual.setFechaHoraFin(fechaHoraActual);
        // crear CAMBIO-NUEVO
        this.crearCamioDeEstado(rechazado, usuario, fechaHoraActual);
        // actualizar estado actual
        this.setEstadoActual(rechazado);
        
        // DEBUGGER  despues
        Debugger.ptrintCambiosDeEstadoDeEvento(this);
    }

    public CambioDeEstado buscarCambioDeEstadoActual(){
        for (CambioDeEstado cambio : this.cambioDeEstados) {
            if (cambio.esEstadoActual()) {
                return cambio;
            }
        }
        return null;
    }

    //crear un nuevo CambioDeEstado añadirlo a los cambiosDeEstados 
    public void crearCamioDeEstado(Estado nuevoEstado, Usuario usuario, LocalDateTime fechaHoraActual){
        CambioDeEstado nuevo = new CambioDeEstado(
            fechaHoraActual, 
            null, //fec hahora fin
            nuevoEstado, 
            usuario.getEmpleado()
        );
        this.cambioDeEstados.add(nuevo);
    }

    //devuelve un vector ordenado con los 3 nombres 
    //[alcance, clasificación, origen, magnitud]
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
        // esto devuelvio String[n][5]
        
        return datosPorSerieTemporal.toArray(new String[0][]);
    }
    
    public String obtenerUbicacionEpicentro(){
        return (this.latitudEpicentro + " : " + this.longitudEpicentro);
    }
    
    public String obtenerUbicacionHipocentro(){
        return (this.latitudHipocentro + " : " + this.longitudHipocentro);
    }
    
}

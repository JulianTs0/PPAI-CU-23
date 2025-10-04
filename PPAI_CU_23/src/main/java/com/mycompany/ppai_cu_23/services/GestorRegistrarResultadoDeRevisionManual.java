package com.mycompany.ppai_cu_23.services;

import com.mycompany.ppai_cu_23.models.Estado;
import com.mycompany.ppai_cu_23.models.EventoSismico;
import com.mycompany.ppai_cu_23.models.Sesion;
import com.mycompany.ppai_cu_23.models.Usuario;
import com.mycompany.ppai_cu_23.utils.DataBase;
import com.mycompany.ppai_cu_23.utils.Debugger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestorRegistrarResultadoDeRevisionManual {
    
    // ATRIBUTO
    private EventoSismico[] eventosAutoDetectados;
    private EventoSismico eventoSeleccionado;
    private Estado estadoBloqueadoARevisar;
    private Estado estadoRechazado;
    private String[] datosEventoSismico;
    private String rutaImagenSismograma;
    private String[][] datosPorSerieTemporal;
    private String[][] datosPorSerieClasificados;
    private LocalDateTime fechaHoraActual;
    // DEPENDENCIA
    private PantallaRegistrarResultadoRevisionManual pantalla;
    private Sesion sesionActual;
    private Usuario logeadoUsuario;
    
    // CONSTRUCTOR
    public GestorRegistrarResultadoDeRevisionManual(PantallaRegistrarResultadoRevisionManual pantalla) {
        //cargar a sesion actual
        this.pantalla = pantalla;
        this.sesionActual = DataBase.cargarSesionActual();
    }
    
    // METODOS_DOMINIO
    
    // PASO 6
    // GESTOR <- PANTALLA
    public void registrarNuevaRevisionManual(){
        
        // buscar AUTO-DETECTADOS
        this.eventosAutoDetectados = this.buscarEventosSismicosAutodetectados();
        
        // ordenar AUTO-DETECTADOS
        this.ordenarEventosFechaHoraDeOcurrencia(this.eventosAutoDetectados);
        // funcion lambda, sin retorno. ahora AUTO-DETECTADOS esta ordenado
        
        // obtener DATOS de los AUTO-DETECTADOS ORDENADOS para pasarselos a PANTALLA
        String[][] datosEventos = new String [this.eventosAutoDetectados.length][4];
        for (int i = 0; i < this.eventosAutoDetectados.length; i++) {
            datosEventos[i][0] = "" + Debugger.formatFechaHora(this.eventosAutoDetectados[i].getFechaHoraOcurrencia());
            datosEventos[i][1] = this.eventosAutoDetectados[i].obtenerUbicacionEpicentro();
            datosEventos[i][2] = this.eventosAutoDetectados[i].obtenerUbicacionHipocentro();
            datosEventos[i][3] = "" + this.eventosAutoDetectados[i].getValorMagnitud();
        }
        
        // DEBUGGER cantidad eventosAutoDetectados encontrados 
        Debugger.mensajeGestor("eventosAutoDetectados: " + this.eventosAutoDetectados.length);
        
        // pasar los DATOS a PANTALLA para que los muestre
        this.pantalla.mostrarEventoSismicoOrdenados(datosEventos);  
    };
    
    // buscar AUTO-DETECTADOS
    public EventoSismico[] buscarEventosSismicosAutodetectados(){
        // LOOP eventos sismicos (TODOS)
        List<EventoSismico> autoDetectados = new ArrayList<>();
        for (EventoSismico evento : DataBase.eventosSismicos) {
            if (evento.esAutoDetectado()) {
                autoDetectados.add(evento);
            }
        }
        return autoDetectados.toArray(new EventoSismico[0]);
    }
    
    // ordenar AUTO-DETECTADOS
    public void ordenarEventosFechaHoraDeOcurrencia(EventoSismico[] eventosAOrdenar){
        Arrays.sort(eventosAOrdenar, (a,b) -> 
                a.getFechaHoraOcurrencia().compareTo(b.getFechaHoraOcurrencia()));
    }
    
    // PASO 8
    // GESTOR <- PANTALLA
    public void tomarSeleccionEventoSismico(int indiceFilaSeleccionada){
        
        // revisar EVENTO-SELECCIONADO
        this.revisarEventoSeleccionado(indiceFilaSeleccionada);
        
        // buscar DATOS-EVENTO-SELECCIONADO
        this.datosPorSerieTemporal = this.buscarDatosDelEventoSismicoSeleccionado();
        
        // clasificar DATOS-EVENTO-SELECCIONADO
        this.datosPorSerieClasificados = this.clasificarDatosPorEstacionSismologica(this.datosPorSerieTemporal);
        
        // PASO 9.3
        // llamar al caso de uso GENERAR-SISMOGRAMA
        this.casoDeUso_GenerarSismograma(datosPorSerieClasificados);
        
        // PASO 9.3
        // GESTOR -> PANTALLA
        this.pantalla.mostrarDatosEventoSismico(this.datosEventoSismico, this.rutaImagenSismograma);
        
        // redundante: la OpcionVisualizarMapa ya estaba visible
        this.pantalla.mostrarOpcionVisualizarMapa();
        // 
        this.pantalla.habilitarModificaiconDatosEvento(this.datosEventoSismico);
        
        this.pantalla.mostrarOpcionesDeAccion();
        
    }
    
    // revisar SELECCIONADO
    public void revisarEventoSeleccionado(int indiceFilaSeleccionada){
        
        // buscar el puntero al EVENTO-SELECCIONADO
        this.eventoSeleccionado = this.eventosAutoDetectados[indiceFilaSeleccionada];
        
        // bloquear el EVENTO-SELECCIONADO
        this.bloquearEventoSelecionado();
        
    }
    
    // buscar el puntero a ESTADO-BLOQUEADO
    public Estado buscarEstadoBloqueadoARevisar(){
        Estado[] estadosCargados = DataBase.cargarEstados();
        for (Estado elemEstado : estadosCargados) {
            if (elemEstado.esAmbitoEventoSismico() 
                    && elemEstado.esBloqueadoARevisar()) {
                return elemEstado;
            }
        }
        return null;
    }
    
    public Estado buscarEstadoRechazado(){
        Estado[] estadosCargados = DataBase.cargarEstados();
        for (Estado elemEstado : estadosCargados) {
            if (elemEstado.esAmbitoEventoSismico() 
                    && elemEstado.esRechazado()) {
                return elemEstado;
            }
        }
        return null;
    }
    
    // bloquear el EVENTO-SELECCIONADO
    public void bloquearEventoSelecionado(){
        
        // buscar FECHA-HORA-ACTUAL
        this.fechaHoraActual = this.getFechaHoraActual();
        
        // buscar puntero a LOGEADO-USUARIO
        this.logeadoUsuario = this.getUsuarioLogeado();
        
        // buscar el puntero a ESTADO-BLOQUEADO
        this.estadoBloqueadoARevisar = this.buscarEstadoBloqueadoARevisar();
        
        // bloquear el EVENTO-SELECCIONADO
        this.eventoSeleccionado.bloquearEnRevision(this.estadoBloqueadoARevisar, this.logeadoUsuario, this.fechaHoraActual);
        
        // DEBUGGER datos del EVENTO-SELECCIONADO-BLOQUEADO
        Debugger.datosEventoSeleccionado(this.eventoSeleccionado);  
        
    }
    
    // buscar FECHA-HORA-ACTUAL
    public LocalDateTime getFechaHoraActual(){
        return LocalDateTime.now();
    }
    
    // buscar puntero a LOGEADO-USUARIO
    public Usuario getUsuarioLogeado(){
        return this.sesionActual.getUsuarioLogeado();
    }
    
    // buscar DATOS-EVENTO-SELECCIONADO
    public String[][] buscarDatosDelEventoSismicoSeleccionado(){
        
        this.datosEventoSismico = this.eventoSeleccionado.obtenerDatosEventoSismico();
        
        Debugger.mostrarVector(datosEventoSismico);
        
        String[][] datosPorSerieTemporal = this.eventoSeleccionado.obtenerDatosSerieTemporal();
        
        Debugger.mostrarDatosPorSerieTemporal(datosPorSerieTemporal);
        
        return datosPorSerieTemporal;
        //
    }
    
    // clasificar DATOS-EVENTO-SELECCIONADO
    public String[][] clasificarDatosPorEstacionSismologica(String[][] datosNoClasificados){
        return datosNoClasificados;
    }
    
    // llamar al caso de uso GENERAR-SISMOGRAMA
    public void casoDeUso_GenerarSismograma(String[][] datosClasificados){
        this.rutaImagenSismograma = ( "/images/sismograma_cordoba.jpeg" );
    }
    
    
    // PASO 15-16
    public void tomarOpcionRechazarEventoSeleccionado(String[] datosSelecionados){
        // [magnitud, alcance, origen, accionSeleccionada]
        // validar MAGNITUD
        // validar ALCANCE
        // validar ORIGEN
        // validar ACCION-SELECCIONADA
        if (this.validarExistenciaMagnitud(datosSelecionados[0]) 
                && this.validarExistenciaAlcance(datosSelecionados[1]) 
                && this.validarExistenciaOrigen(datosSelecionados[2])) {
            if (this.validarAccionSeleccionada(datosSelecionados[3])) {
                // rechazar EVENTO-SELECCIONADO
                this.rechazarEventoSeleccionado();
            }
            else{
                Debugger.mensajeGestor("La accion " + datosSelecionados[3] + " excede del Caso de Uso 23");
            }  
        }
        
        // termina el CASO-DE-USO 23
        this.fin_casoDeUso_23();
    }
    
    // rechazar EVENTO-SELECCIONADO
    public void rechazarEventoSeleccionado(){
        // buscar FECHA-HORA-ACTUAL
        this.fechaHoraActual = this.getFechaHoraActual();
        
        // buscar puntero a LOGEADO-USUARIO
        this.logeadoUsuario = this.getUsuarioLogeado();
        
        // buscar el puntero a ESTADO-RECHAZADO
        this.estadoRechazado = this.buscarEstadoRechazado();
        
        /// rechazar el EVENTO-SELECCIONADO
        this.eventoSeleccionado.rechazar(this.estadoRechazado, this.logeadoUsuario, this.fechaHoraActual);
    }
    
    // validar MAGNITUD
    public boolean validarExistenciaMagnitud(String nombre){
        Debugger.mensajeGestor("Magnitud-" + nombre);
        return (nombre != null);
    }
    
    // validar ALCANCE
    public boolean validarExistenciaAlcance(String nombre){
        Debugger.mensajeGestor("Alcance-" + nombre);
        return (nombre != null);
    }
    
    // validar ORIGEN
    public boolean validarExistenciaOrigen(String nombre){
        Debugger.mensajeGestor("Origen-" + nombre);
        return (nombre != null);
    }
    
    // validar ACCION-SELECCIONADA
    public boolean validarAccionSeleccionada(String nombre){
        Debugger.mensajeGestor("Accion-" + nombre);
        return (nombre.equals(DataBase.nombresAccion[1]));
    }
    
    // termina el CASO-DE-USO 23
    public void fin_casoDeUso_23(){
        Debugger.mensajeGestor("fin_casoDeUso_23()");
        this.pantalla.cambiarPanel(0);
        //System.exit(0);
    }
    
}

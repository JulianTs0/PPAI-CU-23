package com.mycompany.ppai_cu_23.refactor;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.models.EventoSismico;
import com.mycompany.ppai_cu_23.models.Usuario;
import com.mycompany.ppai_cu_23.utils.DataBase;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public abstract class Estado {

    public String nombre;

    public String ambito;

    public Estado(String ambito, String nombre) {
        this.ambito = ambito;
        this.nombre = nombre;
    }

    // Transiciones

    public void adquirirDatos(){
        System.out.println("Metodo no implementado");
    }
    public void cerrar(){
        System.out.println("Metodo no implementado");
    }
    public void confirmar(){
        System.out.println("Metodo no implementado");
    }
    public void rechazar(Usuario usuario, LocalDateTime fechaHoraActual, EventoSismico evento, List<CambioDeEstado> cambiosDeEstado){
        // buscar CAMBIO-ACTUAL
        CambioDeEstado cambioDeEstadoActual = this.buscarCambioDeEstadoActual(cambiosDeEstado);
        // fechahorafin al CAMBIO-ACTUAL
        cambioDeEstadoActual.setFechaHoraFin(fechaHoraActual);
        // Crear estado
        Estado estadoRechazado = new Rechazado(DataBase.nombresAmbito.Evento_Sismico.name(), DataBase.nombresEstado.Rechazado.name());
        // crear CAMBIO-NUEVO
        CambioDeEstado cambioRechazado = this.crearCambioDeEstado(estadoRechazado, usuario, fechaHoraActual);
        // actualizar estado actual
        evento.setEstadoActual(estadoRechazado);
        // actualizar cambio de estado actual
        evento.getCambioDeEstados().add(cambioRechazado);
    }
    public void derivar(){
        System.out.println("Metodo no implementado");
    }
    public void controlarTiempo(){
        System.out.println("Metodo no implementado");
    }
    public void revisar(Usuario usuario, LocalDateTime fechaHoraActual, EventoSismico evento, List<CambioDeEstado> cambiosDeEstado){
        // buscar CAMBIO-ACTUAL
        CambioDeEstado cambioDeEstadoActual = this.buscarCambioDeEstadoActual(cambiosDeEstado);
        // fechahorafin al CAMBIO-ACTUAL
        cambioDeEstadoActual.setFechaHoraFin(fechaHoraActual);
        // Crear estado
        Estado estadoBloqueadoEnRevision = new BloqueadoEnRevision(DataBase.nombresAmbito.Evento_Sismico.name(), DataBase.nombresEstado.Bloqueado_En_Revision.name());
        // crear CAMBIO-NUEVO
        CambioDeEstado cambioBloqueadoEnRevision = this.crearCambioDeEstado(estadoBloqueadoEnRevision, usuario, fechaHoraActual);
        // actualizar estado actual
        evento.setEstadoActual(estadoBloqueadoEnRevision);
        // actualizar cambio de estado actual
        evento.getCambioDeEstados().add(cambioBloqueadoEnRevision);
    }
    public void anular(){
        System.out.println("Metodo no implementado");
    }

    // Verificaciones

    public boolean esPendienteDeRevision(){
        return (this.nombre.equals(DataBase.nombresEstado.Pendiente_De_Revision.name()));
    }

    public boolean esAutoDetectado(){
        return (this.nombre.equals(DataBase.nombresEstado.Auto_Detectado.name()));
    }

    public boolean esBloqueadoARevisar(){
        return (this.nombre.equals(DataBase.nombresEstado.Bloqueado_En_Revision.name()));
    }

    public boolean esRechazado(){
        return (this.nombre.equals(DataBase.nombresEstado.Rechazado.name()));
    }

    // Ambito

    public boolean esAmbitoEventoSismico(){
        return (this.ambito.equals(DataBase.nombresAmbito.Evento_Sismico.name()));
    }

    public CambioDeEstado buscarCambioDeEstadoActual(List<CambioDeEstado> cambiosDeEstado){
        for (CambioDeEstado cambio : cambiosDeEstado) {
            if (cambio.esEstadoActual()) {
                return cambio;
            }
        }
        return null;
    }

    public CambioDeEstado crearCambioDeEstado(Estado nuevoEstado, Usuario usuario, LocalDateTime fechaHoraActual){
        CambioDeEstado nuevo = new CambioDeEstado(
            fechaHoraActual,
            null,
            nuevoEstado,
            usuario.getEmpleado()
        );
        return nuevo;
    }

}

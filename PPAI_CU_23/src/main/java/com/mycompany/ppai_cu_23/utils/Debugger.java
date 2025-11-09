package com.mycompany.ppai_cu_23.utils;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.models.EventoSismico;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Debugger {
    
    //
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    
    public static String formatFechaHora(LocalDateTime fh) {
        if (fh != null) {
            return "" + fh.format(formatter);
        }
        return null;
    }
    
    
    public static void debug(Object mensaje){
        System.out.println("Debug: " + mensaje);
    }
    
    public static void mensajeGestor(String mensaje){
        System.out.println("Gestor: " + mensaje);
    }
    
    public static void mensajePantalla(String mensaje){
        System.out.println("Pantalla: " + mensaje);
    }
    
    public static void mensajeEvento(String mensaje){
        System.out.println("Evento: " + mensaje);
    }
    
    public static void datosEventoSeleccionado(EventoSismico evento){
        mensajeGestor("ES_Selec: " + eventoToString(evento));
    }
    
    
    
    public static String eventoToString (EventoSismico evento){
        return ( 
            formatFechaHora(evento.getFechaHoraOcurrencia()) + " | " + 
            evento.obtenerUbicacionEpicentro()  + " | " + 
            evento.obtenerUbicacionHipocentro()  + " | " + 
            evento.getValorMagnitud()  + " | " +

            evento.getAlcanceSismo().getNombre()  + " | " +
            evento.getClasificacionSismo().getNombre()  + " | " +
            evento.getOrigenDeGeneracion().getNombre()  + " | " +
                
            evento.buscarCambioDeEstadoActual().getEstado().getNombre() + " | " +
            evento.getEstadoActual().getNombre()
        );
    }
    
    public static void mostrarVector(Object[] vector){
        String mensaje = "Al-Cl-Or-Ma : | ";
        for (Object obj: vector) {
            mensaje += obj + " | ";
        }
        System.out.println(mensaje);
    }
    
    public static void mostrarDatosPorSerieTemporal(String[][] datos){
        String matriz= "| fechaHoraMuestra | Denominacion | Dato | nombreEstacionSismologica |\n";
        for (String [] fila : datos) {
            matriz += "| ";
            for (String celda : fila) {
                matriz += celda + " | ";
            }
            matriz += "\n";
        }
        mensajeGestor("Lista de DatosPorSerieTemporal");
        System.out.println(matriz);
    }
    
    public static void ptrintCambiosDeEstadoDeEvento(EventoSismico evento){
        String matriz= "| FechaHoraInicio | FechaHoraFin | Empleado | Estado |\n";
        for (CambioDeEstado cambio : evento.getCambioDeEstados()) {
            matriz += (
                    "| " + 
                    ("" + formatFechaHora(cambio.getFechaHoraInicio())) + " | " +
                    ("" + formatFechaHora(cambio.getFechaHoraFin())) + " | " +
                    cambio.getEmpleado().getNombre()  + " | " +
                    cambio.getEstado().getNombre()+ " | "
                    + "\n"
            );
        }
        mensajeEvento("Lista de cambiosDeEstadoDeEvento: " + evento.getCambioDeEstados().size());
        System.out.println(matriz);
    }
    
}
//formatFechaHora()


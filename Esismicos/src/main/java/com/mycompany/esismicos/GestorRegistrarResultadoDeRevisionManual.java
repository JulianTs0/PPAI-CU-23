package com.mycompany.esismicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; // Necesario para Collections.sort
import java.util.Comparator;  // Necesario para Comparator

public class GestorRegistrarResultadoDeRevisionManual {

    private List<Object[]> eventosParaGrilla;
    // --- Atributos del Gestor ---

    private String nombreAlcance;
    private String nombreClasificacion;
    private String nombreOrigen;
    private LocalDateTime fechaHoraRevision;

    private EventoSismico eventoSeleccionado;
    private Sesion sesionActual;

    private PantallaRegistrarResultadoDeRevisionManual pantalla;
    private List<EventoSismico> todosLosEventosDelSistema; // NUEVO: Atributo para almacenar todos los eventos


    // Constructor
    public GestorRegistrarResultadoDeRevisionManual(
            String nombreAlcance,
            String nombreClasificacion,
            String nombreOrigen,
            LocalDateTime fechaHoraRevision,
            PantallaRegistrarResultadoDeRevisionManual pantalla,
            List<EventoSismico> todosLosEventosDelSistema,
            Sesion sesionActual) { // NUEVO PARÁMETRO
        this.nombreAlcance = nombreAlcance;
        this.nombreClasificacion = nombreClasificacion;
        this.nombreOrigen = nombreOrigen;
        this.fechaHoraRevision = fechaHoraRevision;

        this.eventosParaGrilla = new ArrayList<>();

        this.pantalla = pantalla;
        this.todosLosEventosDelSistema = todosLosEventosDelSistema; // Asigna la lista de eventos
        this.sesionActual = sesionActual;
        System.out.println("GestorRegistrarResultadoDeRevisionManual: Instancia (Constructor Largo) creada con valores iniciales y referencia a Pantalla y Eventos.");
    }

    // --- Getters y Setters ---

    public String getNombreAlcance() {
        return nombreAlcance;
    }

    public void setNombreAlcance(String nombreAlcance) {
        this.nombreAlcance = nombreAlcance;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public LocalDateTime getFechaHoraRevision() {
        return fechaHoraRevision;
    }

    public void setFechaHoraRevision(LocalDateTime fechaHoraRevision) {
        this.fechaHoraRevision = fechaHoraRevision;
    }

   
    public List<Object[]> getEventosParaGrilla() {
        return eventosParaGrilla;
    }

    public Sesion getSesionActual() {
        return sesionActual;
    }

    // --- Métodos del Gestor (vacíos) ---

    public void registrarNuevaRevisionManual() {
        System.out.println("GestorRegistrarResultadoDeRevisionManual: Método registrarNuevaRevisionManual ejecutado.");
        if (pantalla != null) {
            pantalla.actualizarEstadoPantalla("Iniciando búsqueda de eventos pendientes...");
        }

        // El gestor obtiene la lista de filas para la grilla.
        // Ahora, el método 'buscarEventosSismicosAutodetectadosNoRevisados' devuelve la lista.
        this.eventosParaGrilla = buscarEventosSismicosAutodetectadosNoRevisados();

        ordenarFechaHoraDeOcurrencia();

        if (pantalla != null) {
            pantalla.actualizarEstadoPantalla("Eventos pendientes cargados. Hay " + eventosParaGrilla.size() + " eventos para revisar.");
            
            pantalla.mostrarEventoSismicoOrdenados(this.eventosParaGrilla);
        }
    }
    
    // CU-23 PASO-6 
    public List<Object[]> buscarEventosSismicosAutodetectadosNoRevisados() {
        System.out.println("Gestor: Ejecutando método buscarEventosSismicosAutodetectadosNoRevisados...");

        // Esta es la lista que construiremos y devolveremos.
        List<Object[]> filasParaGrilla = new ArrayList<>();

        // Usa el atributo todosLosEventosDelSistema que fue inyectado
        if (this.todosLosEventosDelSistema == null || this.todosLosEventosDelSistema.isEmpty()) {
            System.out.println("Gestor: No se encontraron eventos sísmicos en el sistema.");
            return filasParaGrilla; // Devuelve una lista vacía
        }

        // Paso 1: Filtrar eventos pendientes
        List<EventoSismico> eventosPendientesFiltrados = new ArrayList<>();
        for (EventoSismico evento : this.todosLosEventosDelSistema) { // Itera sobre el atributo
            System.out.println("Gestor: Evaluando evento: " + evento.toString());

            boolean esPendiente = evento.esPendienteDeRevision();

            if (!esPendiente) {
                System.out.println("Gestor: Evento NO está pendiente de revisión. Saltando iteración.");
                continue;
            }

            System.out.println("Gestor: Evento SÍ está pendiente de revisión. Agregando a la lista de resultados...");
            eventosPendientesFiltrados.add(evento);
        }

        // Paso 2 (anteriormente Paso 3): Crear los Object[] a partir de los eventos filtrados y agregarlos a la lista final
        for (EventoSismico evento : eventosPendientesFiltrados) {
            LocalDateTime fechaHora = evento.getFechaHoraOcurrencia();
            double[] ubicacion = evento.obtenerUbicacion(); // Espera [latEpicentro, lonEpicentro, latHipocentro, lonHipocentro]
            double magnitudEvento = evento.getValorMagnitud();

            // Creamos un array de Object con los datos para la grilla y la referencia al objeto original
            Object[] rowData = new Object[]{
                fechaHora,                      // [0] Fecha y Hora Ocurrencia (LocalDateTime)
                ubicacion[0],                   // [1] Latitud Epicentro (double)
                ubicacion[1],                   // [2] Longitud Epicentro (double)
                ubicacion[2],                   // [3] Latitud Hipocentro (double)  <-- CORREGIDO
                ubicacion[3],                   // [4] Longitud Hipocentro (double) <-- CORREGIDO
                magnitudEvento,                 // [5] Magnitud (double)
                evento                          // [6] ¡La referencia al EventoSismico original!
            };
            filasParaGrilla.add(rowData);
            System.out.println("Gestor: Fila para grilla creada para " + fechaHora + " y agregada.");
        }
        System.out.println("Gestor: Finalizada la búsqueda y preparación de eventos pendientes (sin ordenar). Total filas generadas: " + filasParaGrilla.size());
        return filasParaGrilla; // Devuelve la lista de Object[]
    }

    
    public void ordenarFechaHoraDeOcurrencia() {
        System.out.println("Gestor: Ejecutando método ordenarFechaHoraDeOcurrencia...");

        if (this.eventosParaGrilla == null || this.eventosParaGrilla.isEmpty()) {
            System.out.println("Gestor: La lista de eventos para la grilla está vacía o no ha sido cargada. No se puede ordenar.");
            return;
        }

        Collections.sort(this.eventosParaGrilla, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] fila1, Object[] fila2) {
                LocalDateTime fechaHora1 = (LocalDateTime) fila1[0];
                LocalDateTime fechaHora2 = (LocalDateTime) fila2[0];
                return fechaHora1.compareTo(fechaHora2);
            }
        });
        System.out.println("Gestor: Lista de eventos para la grilla ordenada por fecha y hora de ocurrencia.");
        // Si la pantalla ya estuviera mostrando estos datos, necesitaría un "refrescar" o "actualizar"
        // pantalla.actualizarGrilla();
    }

    public void tomarSeleccionEventoSismico(int indiceFilaSeleccionada) {
        System.out.println("\n--- Gestor: Método tomarSeleccionEventoSismico (desarrollado) ejecutado ---");
        System.out.println("Gestor: Recibido índice de fila seleccionada: " + indiceFilaSeleccionada);

        // Validar que el índice sea válido para evitar errores.
        if (this.eventosParaGrilla == null || indiceFilaSeleccionada < 0 || indiceFilaSeleccionada >= this.eventosParaGrilla.size()) {
            System.err.println("Gestor: ERROR - Índice de fila no válido o lista de eventos para grilla vacía.");
            this.eventoSeleccionado = null; // Asegura que no haya un evento seleccionado incorrectamente
            pantalla.actualizarEstadoPantalla("Error: Selección de evento no válida.");
            return;
        }

        // Obtener el array de Object que representa la fila seleccionada
        Object[] filaSeleccionada = this.eventosParaGrilla.get(indiceFilaSeleccionada);

        // El EventoSismico original está almacenado en el índice [6] de ese Object[]
        // Se realiza un casting para recuperarlo como EventoSismico
        this.eventoSeleccionado = (EventoSismico) filaSeleccionada[6];

        if (this.eventoSeleccionado != null) {
            System.out.println("Gestor: ¡EventoSismico seleccionado y establecido correctamente!");
            System.out.println("Gestor: Detalles del Evento Sísmico seleccionado:");
            System.out.println("  Fecha y Hora: " + this.eventoSeleccionado.getFechaHoraOcurrencia());
            System.out.println("  Magnitud: " + this.eventoSeleccionado.getValorMagnitud());
            System.out.println("  Ubicación Epicentro: Lat " + this.eventoSeleccionado.obtenerUbicacion()[0] +
                               " Lon " + this.eventoSeleccionado.obtenerUbicacion()[1]);
            // Puedes agregar más detalles si EventoSismico tiene un buen toString() o más getters
            System.out.println("  Objeto EventoSismico: " + this.eventoSeleccionado.toString());

            // Actualizar la pantalla con un mensaje que confirme la selección
            pantalla.actualizarEstadoPantalla("Evento " + this.eventoSeleccionado.getFechaHoraOcurrencia().toLocalTime() +
                                              " (Mag: " + this.eventoSeleccionado.getValorMagnitud() + ") seleccionado.");

            // A partir de este punto, this.eventoSeleccionado puede ser utilizado
            // para continuar con el resto de la lógica del caso de uso (ej. mostrar datos, etc.)
            // Por ejemplo:
            // this.mostrarDatosDetalladosEnPantalla();
        } else {
            System.err.println("Gestor: ERROR - El objeto EventoSismico en el índice [6] era nulo.");
            pantalla.actualizarEstadoPantalla("Error: Evento no recuperado correctamente.");
        }
        System.out.println("--- Fin del método tomarSeleccionEventoSismico ---");
    }

    public Empleado obtenerEmpleadoActual() {
        return this.sesionActual.getEmpleado();
    }

    public void getFechaActual() {}
    public void buscarEstadoBloqueadoEnRevision() {}
    public void buscarDatosDelEventoSismicoSeleccionado() {}
    public void clasificarDatosPorEstacionSismologica() {}
    public void tomarOpcionVisualizarMapa() {}
    public void tomarOpcionModificarDatos() {}
    public void tomarOpcionesDeEvento() {}
    public void validarMagnitud() {}
    public void validarAlcance() {}
    public void validarOrigenDeGen() {}
    public void validarAccion() {}
    public void buscarEstadoRechazado() {}
    public void obtenerEventosAutoDetectadosNoRevisados() {}
    public void finCU() {}

    // --- Método toString() ---
    @Override
    public String toString() {
        return "GestorRegistrarResultadoDeRevisionManual{" +
               ", nombreAlcance='" + nombreAlcance + '\'' +
               ", nombreClasificacion='" + nombreClasificacion + '\'' +
               ", nombreOrigen='" + nombreOrigen + '\'' +
               ", fechaHoraRevision=" + fechaHoraRevision +
               '}';
    }
}

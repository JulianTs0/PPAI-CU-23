package com.mycompany.esismicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections; // Necesario para Collections.sort
import java.util.Comparator;  // Necesario para Comparator
import java.util.Map; // Para clasificar los datos por estación
import java.util.HashMap; // Implementación de Map

public class GestorRegistrarResultadoDeRevisionManual {

    private List<Object[]> eventosParaGrilla;
    // --- Atributos del Gestor ---

    private String nombreAlcance;
    private String nombreClasificacion;
    private String nombreOrigen;
    private LocalDateTime fechaHoraRevision;
    private LocalDateTime fechaHoraBloqueado;

    private List<Estado> todosLosEstadosDelSistema; // <-- NUEVO ATRIBUTO: Colección de estados
    private Estado estadoBloqueadoARevisar;

    private EventoSismico eventoSeleccionado;
    private Sesion sesionActual;
    private Empleado logeadoEmpleado;

    private PantallaRegistrarResultadoDeRevisionManual pantalla;
    private List<EventoSismico> todosLosEventosDelSistema; // NUEVO: Atributo para almacenar todos los eventos

    // ¡NUEVO ATRIBUTO para guardar los datos de las series temporales!
    private List<Object[]> datosSeriesTemporales;


    // Constructor
    public GestorRegistrarResultadoDeRevisionManual(
            String nombreAlcance,
            String nombreClasificacion,
            String nombreOrigen,
            LocalDateTime fechaHoraRevision,
            PantallaRegistrarResultadoDeRevisionManual pantalla,
            List<EventoSismico> todosLosEventosDelSistema,
            Sesion sesionActual,
            List<Estado> todosLosEstadosDelSistema) { // NUEVO PARÁMETRO
        this.nombreAlcance = nombreAlcance;
        this.nombreClasificacion = nombreClasificacion;
        this.nombreOrigen = nombreOrigen;
        this.fechaHoraRevision = fechaHoraRevision;

        this.eventosParaGrilla = new ArrayList<>();
        // Inicializa la nueva lista de datos de series temporales
        this.datosSeriesTemporales = new ArrayList<>(); // ¡Inicialización del nuevo atributo!


        this.pantalla = pantalla;
        this.todosLosEventosDelSistema = todosLosEventosDelSistema; // Asigna la lista de eventos
        this.sesionActual = sesionActual;
        this.todosLosEstadosDelSistema = todosLosEstadosDelSistema;
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

    public List<Object[]> getDatosSeriesTemporales() {
        return new ArrayList<>(this.datosSeriesTemporales); // Retorna una copia defensiva
    }

    // --- Métodos del Gestor (vacíos) ---

    // ESTO REPRESENTA EL CASO DE USO PRINCIPAL
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

            // *** AQUÍ es donde se invoca obtenerEmpleadoActual() ***
            // Se llama después de que el evento ha sido seleccionado (this.eventoSeleccionado ya tiene un valor).
            System.out.println("Gestor: Invocando obtenerEmpleadoActual() después de la selección del evento.");
            this.obtenerEmpleadoActual(); // Llama al método del gestor para obtener el empleado de la sesión

            System.out.println("Gestor: Invocando getFechaHoraActual() después de obtener el empleado.");
            this.getFechaHoraActual();

            // --- NUEVO PASO: Buscar el estado "Bloqueado A Revisar" ---
            System.out.println("Gestor: Invocando buscarEstadoBloqueadoEnRevision().");
            this.buscarEstadoBloqueadoEnRevision(); // <--- LLAMADA AL NUEVO MÉTODO AQUÍ

            // --- NUEVO PASO: Ejecutar bloquearEnRevision() en el evento seleccionado ---
            // Se ejecuta si se encontró el estado Bloqueado A Revisar y el evento está seleccionado.
            if (this.estadoBloqueadoARevisar != null) {
                System.out.println("Gestor: Invocando bloquearEnRevision() en el EventoSismico seleccionado.");
                this.eventoSeleccionado.bloquearEnRevision(
                        this.fechaHoraBloqueado,
                        this.logeadoEmpleado,
                        this.estadoBloqueadoARevisar
                );
                if (pantalla != null) {
                    pantalla.actualizarEstadoPantalla("Evento bloqueado para revisión: " + this.eventoSeleccionado.getFechaHoraOcurrencia().toLocalTime());
                }
            } else {
                System.err.println("Gestor: ADVERTENCIA - No se pudo bloquear el evento, estado 'Bloqueado A Revisar' no encontrado.");
                if (pantalla != null) {
                    pantalla.actualizarEstadoPantalla("Error: No se pudo bloquear el evento (estado no encontrado).");
                }
            }

            this.buscarDatosDelEventoSismicoSeleccionado();

            this.clasificarDatosPorEstacionSismologica();

        } else {
            System.err.println("Gestor: ERROR - El objeto EventoSismico en el índice [6] era nulo.");
            pantalla.actualizarEstadoPantalla("Error: Evento no recuperado correctamente.");
        }
        System.out.println("--- Fin del método tomarSeleccionEventoSismico ---");
    }

    public void obtenerEmpleadoActual() {
        System.out.println("\n--- Gestor: Método obtenerEmpleadoActual() ejecutado ---");
        if (this.sesionActual != null) {
            // El Gestor le pide a la Sesión su empleado utilizando el método obtenerEmpleadoActual() de la Sesion
            this.logeadoEmpleado = this.sesionActual.obtenerEmpleadoActual(); // Llama al método de la Sesion

            if (this.logeadoEmpleado != null) {
                System.out.println("Gestor: Empleado logeado obtenido y guardado: " +
                        this.logeadoEmpleado.getNombreCompleto() + " (" + this.logeadoEmpleado.getCargo() + ")");
                // Solo utiliza actualizarEstadoPantalla para mostrar el nombre del empleado
                if (pantalla != null) {
                    pantalla.actualizarEstadoPantalla("Empleado de sesión: " + this.logeadoEmpleado.getNombreCompleto());
                }
            } else {
                System.err.println("Gestor: ERROR - La sesión actual no tiene un empleado asociado.");
                if (pantalla != null) {
                    pantalla.actualizarEstadoPantalla("Error: Empleado no encontrado en la sesión.");
                }
            }
        } else {
            System.err.println("Gestor: ERROR - No hay una sesión actual activa en el gestor.");
            if (pantalla != null) {
                pantalla.actualizarEstadoPantalla("Error: No hay sesión activa para obtener empleado.");
            }
        }
        System.out.println("--- Fin del método obtenerEmpleadoActual() del Gestor ---");
    }

    public void getFechaHoraActual() {
        System.out.println("\n--- Gestor: Método getFechaHoraActual() ejecutado ---");
        // Obtener la fecha y hora actual del sistema y guardarla en el nuevo atributo
        this.fechaHoraBloqueado = LocalDateTime.now();
        System.out.println("Gestor: Fecha y hora actual obtenida y guardada en 'fechaHoraBloqueado': " + this.fechaHoraBloqueado);

        // Si necesitas mostrar esto en la pantalla, puedes usar actualizarEstadoPantalla
        if (pantalla != null) {
            pantalla.actualizarEstadoPantalla("Fecha y hora de 'bloqueo' registrada: " +
                    this.fechaHoraBloqueado);
        }
        System.out.println("--- Fin del método getFechaHoraActual() ---");
    }

    /**
     * Itera sobre la colección de estados para buscar el estado
     * con ámbito "Evento Sismico" y nombre "Bloqueado A Revisar".
     * Si lo encuentra, guarda el puntero en 'estadoBloqueadoARevisar'.
     */
    public void buscarEstadoBloqueadoEnRevision() {
        System.out.println("\n--- Gestor: Método buscarEstadoBloqueadoEnRevision() ejecutado ---");

        if (this.todosLosEstadosDelSistema == null || this.todosLosEstadosDelSistema.isEmpty()) {
            System.err.println("Gestor: ERROR - La colección de estados del sistema está vacía o no ha sido cargada.");
            if (pantalla != null) {
                pantalla.actualizarEstadoPantalla("Error: No se pudo buscar estados, la lista de estados está vacía.");
            }
            return;
        }

        // Iterar sobre la colección de estados
        for (Estado estado : this.todosLosEstadosDelSistema) {
            System.out.println("Gestor: Evaluando estado: " + estado.getNombreEstado() + " (Ámbito: " + estado.getAmbito() + ")");

            // Paso 1: Comprobar si el ámbito es "Evento Sismico"
            if (estado.esAmbitoEventoSismico()) {
                System.out.println("Gestor: El estado '" + estado.getNombreEstado() + "' tiene ámbito 'Evento Sismico'.");

                // Paso 2: Si el ámbito es correcto, comprobar si el nombre es "Bloqueado A Revisar"
                if (estado.esEstadoBloqueadoARevisar()) {
                    System.out.println("Gestor: ¡Estado 'Bloqueado A Revisar' encontrado! Nombre: " + estado.getNombreEstado());
                    // Guarda el puntero del estado encontrado en la variable del gestor
                    this.estadoBloqueadoARevisar = estado;
                    if (pantalla != null) {
                        pantalla.actualizarEstadoPantalla("Estado 'Bloqueado A Revisar' encontrado y guardado.");
                    }
                    // Una vez encontrado, podemos salir del bucle si solo esperamos uno
                    return;
                } else {
                    System.out.println("Gestor: El estado no es 'Bloqueado A Revisar'. Saltando al siguiente.");
                }
            } else {
                System.out.println("Gestor: El ámbito del estado '" + estado.getNombreEstado() + "' NO es 'Evento Sismico'. Saltando al siguiente.");
            }
        }

        // Si el bucle termina y el estado no se encontró
        if (this.estadoBloqueadoARevisar == null) {
            System.err.println("Gestor: ADVERTENCIA - No se encontró el estado 'Bloqueado A Revisar' con ámbito 'Evento Sismico'.");
            if (pantalla != null) {
                pantalla.actualizarEstadoPantalla("Advertencia: Estado 'Bloqueado A Revisar' no encontrado.");
            }
        }
        System.out.println("--- Fin del método buscarEstadoBloqueadoEnRevision() ---");
    }

    public void buscarDatosDelEventoSismicoSeleccionado() {
        System.out.println("\n--- Gestor: Método buscarDatosDelEventoSismicoSeleccionado() ejecutado ---");
        if (this.eventoSeleccionado == null) {
            System.err.println("Gestor: ERROR - No hay un evento sísmico seleccionado. No se pueden buscar datos.");
            if (pantalla != null) {
                pantalla.actualizarEstadoPantalla("Error: No hay evento seleccionado para mostrar datos.");
            }
            return;
        }

        System.out.println("Gestor: Invocando tomarDatosDelEventoSismicoSeleccionado() en el evento seleccionado.");
        // El gestor le pide al evento seleccionado que tome sus datos
        List<String> datosDelEvento = this.eventoSeleccionado.tomarDatosDelEventoSismicoSeleccionado();

        if (datosDelEvento != null && !datosDelEvento.isEmpty()) {
            System.out.println("Gestor: Datos del evento obtenidos correctamente. Pasando a la pantalla.");
            // El gestor le pasa los datos a la pantalla
            if (pantalla != null) {
                // NOTA: Tu método mostrarDatosEventoSismico() en Pantalla no recibe parámetros.
                // Si quieres que muestre estos datos, deberías modificarlo para que acepte un List<String> o similar.
                // Por ejemplo: pantalla.mostrarDatosEventoSismico(datosDelEvento);
                // Y dentro de Pantalla: public void mostrarDatosEventoSismico(List<String> datos) { ... }
                pantalla.mostrarDatosEventoSismico(datosDelEvento);
                System.out.println("Gestor: Ejecutando getSerieTemporal() en el evento sísmico seleccionado.");

                this.datosSeriesTemporales = this.eventoSeleccionado.getSerieTemporal();

                pantalla.actualizarEstadoPantalla("Datos del evento sísmico obtenidos y listos para mostrar.");
            }
        } else {
            System.err.println("Gestor: ADVERTENCIA - El evento seleccionado no retornó datos.");
            if (pantalla != null) {
                pantalla.actualizarEstadoPantalla("Advertencia: No se pudieron obtener los datos del evento.");
            }
        }
        System.out.println("--- Fin del método buscarDatosDelEventoSismicoSeleccionado() ---");
    }

    /**
     * Clasifica los datos de las series temporales por estación sismológica
     * y luego invoca el método para generar los sismogramas.
     */
    public void clasificarDatosPorEstacionSismologica() {
        System.out.println("\n--- Gestor: Método clasificarDatosPorEstacionSismologica() ejecutado ---");

        if (this.datosSeriesTemporales == null || this.datosSeriesTemporales.isEmpty()) {
            System.out.println("Gestor: No hay datos de series temporales para clasificar.");
            if (pantalla != null) {
                pantalla.actualizarEstadoPantalla("No hay datos de series temporales para generar sismogramas.");
            }
            return;
        }

        Map<String, List<Object[]>> datosClasificadosPorEstacion = new HashMap<>();

        for (Object[] muestra : this.datosSeriesTemporales) {
            String nombreEstacion = (String) muestra[4]; // El nombre de la estación está en el índice [4]

            datosClasificadosPorEstacion.computeIfAbsent(nombreEstacion, k -> new ArrayList<>()).add(muestra);
        }

        System.out.println("Gestor: Datos de series temporales clasificados por estación.");
        System.out.println("Gestor: Se encontraron " + datosClasificadosPorEstacion.size() + " estaciones distintas.");

        for (Map.Entry<String, List<Object[]>> entry : datosClasificadosPorEstacion.entrySet()) {
            System.out.println("  - Estación: '" + entry.getKey() + "' con " + entry.getValue().size() + " muestras.");
        }

        System.out.println("Gestor: Invocando casoDeUsoGenerarSismograma() con los datos clasificados.");
        Map<String, String> rutasSismogramasGenerados = this.casoDeUsoGenerarSismograma(datosClasificadosPorEstacion);

        if (pantalla != null) {
            pantalla.actualizarEstadoPantalla("Datos clasificados por estación. Sismogramas simulados generados.");
            // *** MODIFICACIÓN CLAVE: Invocar mostrarOpcionVisualizarMapa con las rutas ***
            this.pantalla.mostrarOpcionVisualizarMapa(rutasSismogramasGenerados);
        }
        System.out.println("--- Fin del método clasificarDatosPorEstacionSismologica() ---");
    }

    /**
     * STUB: Este método simula la generación de sismogramas y devuelve
     * un mapa con las rutas de las imágenes de sismogramas asociadas a cada estación.
     *
     * @param datosClasificadosPorEstacion Un mapa con los datos de las series temporales clasificados por estación.
     * @return Un Map donde la clave es el nombre de la estación (String) y el valor
     * es la ruta del archivo de imagen simulado (String) para esa estación.
     */
    public Map<String, String> casoDeUsoGenerarSismograma(Map<String, List<Object[]>> datosClasificadosPorEstacion) {
        System.out.println("\n--- Gestor: STUB del método casoDeUsoGenerarSismograma() ejecutado ---");
        System.out.println("Gestor: Recibidos datos clasificados para simular la generación de sismogramas.");

        Map<String, String> rutasSismogramasPorEstacion = new HashMap<>();

        // Iterar sobre las estaciones que tienen datos
        for (String nombreEstacion : datosClasificadosPorEstacion.keySet()) {
            String rutaImagen = "";
            // Asignar una ruta de imagen simulada basada en el nombre exacto de la estación
            switch (nombreEstacion) {
                case "Estación Salta":
                    rutaImagen = "images/sismograma_salta.png";
                    break;
                case "Estación Córdoba":
                    rutaImagen = "images/sismograma_cordoba.png";
                    break;
                case "Estación Buenos Aires":
                    rutaImagen = "images/sismograma_buenos_aires.png";
                    break;
                default:
                    System.err.println("Gestor: Estación desconocida para sismograma simulado: '" + nombreEstacion + "'. Asignando imagen por defecto.");
                    rutaImagen = "images/sismograma_default.png"; // O una imagen por defecto
                    break;
            }
            rutasSismogramasPorEstacion.put(nombreEstacion, rutaImagen);
            System.out.println("  - Simulado sismograma para '" + nombreEstacion + "': " + rutaImagen);
        }

        if (pantalla != null) {
            pantalla.actualizarEstadoPantalla("STUB: Rutas de sismogramas simuladas generadas.");
            // Aquí, el Gestor podría llamar a un nuevo método en la Pantalla
            // para mostrar estos sismogramas. Por ejemplo:
            // pantalla.mostrarSismogramas(rutasSismogramasPorEstacion);
        }
        System.out.println("--- Fin del STUB del método casoDeUsoGenerarSismograma() ---");
        return rutasSismogramasPorEstacion;
    }

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

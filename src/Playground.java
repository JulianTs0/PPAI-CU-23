import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities; // Importa SwingUtilities

public class Playground {

    public static void main(String[] args) {
        System.out.println("Iniciando el Playground de Eventos Sísmicos...");
        System.out.println("----------------------------------------------\n");

        // Declaración de la lista de eventos sísmicos
        List<EventoSismico> eventosSismicos = new ArrayList<>();

        // 1. Crear instancias de clases base (que no dependen de otras en su constructor)
        // Empleados
        Empleado emp1 = new Empleado("Juan", "Pérez", "Analista Sísmico");
        Empleado emp2 = new Empleado("María", "González", "Técnica de Campo");
        Empleado emp3 = new Empleado("Carlos", "Rodríguez", "Jefe de Observatorio");

        // Estados
        Estado estadoPendiente = new Estado("EventoSismico", "Pendiente de Revisión");
        Estado estadoEnRevision = new Estado("EventoSismico", "En Revisión");
        Estado estadoRechazado = new Estado("EventoSismico", "Rechazado");
        Estado estadoValidado = new Estado("EventoSismico", "Validado");
        Estado estadoActivoSismografo = new Estado("Sismografo", "Activo");
        Estado estadoInactivoSismografo = new Estado("Sismografo", "Inactivo");
        Estado estadoNormalSerie = new Estado("SerieTemporal", "Normal");
        Estado estadoAlertaSerie = new Estado("SerieTemporal", "Alerta");


        // Tipos de Dato para Muestras Sísmicas
        TipoDeDato tipoMagnitud = new TipoDeDato("Magnitud", "Richter");
        TipoDeDato tipoProfundidad = new TipoDeDato("Profundidad", "km");
        TipoDeDato tipoAceleracion = new TipoDeDato("Aceleración", "m/s²");

        // Clasificaciones de Sismo
        ClasificacionSismo clasifLeve = new ClasificacionSismo(0.0, 50.0, "Leve");
        ClasificacionSismo clasifModerado = new ClasificacionSismo(50.1, 300.0, "Moderado");
        ClasificacionSismo clasifProfundo = new ClasificacionSismo(300.1, 700.0, "Profundo");

        // Orígenes de Generación
        OrigenDeGeneracion origenTectonico = new OrigenDeGeneracion("Movimiento de placas tectónicas", "Tectónico");
        OrigenDeGeneracion origenVolcanico = new OrigenDeGeneracion("Actividad volcánica", "Volcánico");
        OrigenDeGeneracion origenAntropico = new OrigenDeGeneracion("Inducido por actividad humana", "Antrópico");

        // Alcances de Sismo
        AlcanceSismo alcanceLocal = new AlcanceSismo("Sentido en un área pequeña", "Local");
        AlcanceSismo alcanceRegional = new AlcanceSismo("Sentido en una región", "Regional");
        AlcanceSismo alcanceGlobal = new AlcanceSismo("Sentido en grandes extensiones", "Global");

        // Estaciones Sismológicas
        EstacionSismologica est1 = new EstacionSismologica("EST-001", "DocCert_123", LocalDateTime.of(2023, 1, 15, 10, 0), -31.4167, -64.1833, "Estación Córdoba", "CERT-A-001");
        EstacionSismologica est2 = new EstacionSismologica("EST-002", "DocCert_456", LocalDateTime.of(2023, 3, 20, 8, 30), -34.6037, -58.3816, "Estación Buenos Aires", "CERT-B-002");
        EstacionSismologica est3 = new EstacionSismologica("EST-003", "DocCert_789", LocalDateTime.of(2022, 11, 5, 14, 45), -24.7828, -65.4107, "Estación Salta", "CERT-C-003");

        // Sismógrafos (asociados a estaciones y estados)
        Sismografo sismografo1 = new Sismografo(LocalDateTime.of(2024, 2, 1, 9, 0), "SISM-001", "SN12345", "Sismógrafo Principal Cba", est1, estadoActivoSismografo);
        Sismografo sismografo2 = new Sismografo(LocalDateTime.of(2024, 3, 10, 11, 30), "SISM-002", "SN67890", "Sismógrafo Secundario BA", est2, estadoActivoSismografo);
        Sismografo sismografo3 = new Sismografo(LocalDateTime.of(2024, 4, 15, 16, 0), "SISM-003", "SN11223", "Sismógrafo Remoto Salta", est3, estadoActivoSismografo);

        // 2. Creación de 5 Eventos Sísmicos con sus relaciones completas


        // --- Evento Sísmico 1 ---
        // Fecha y hora del evento
        LocalDateTime fechaHoraOcurrencia1 = LocalDateTime.of(2024, 5, 20, 14, 30, 0);
        LocalDateTime fechaHoraFin1 = fechaHoraOcurrencia1.plusHours(1);

        EventoSismico evento1 = new EventoSismico(
            fechaHoraFin1, fechaHoraOcurrencia1,
            -33.5, -33.4, // Latitud Hipocentro/Epicentro
            -69.5, -69.4, // Longitud Hipocentro/Epicentro
            6.2, "Regional", "Moderado", "Tectónico",
            clasifModerado, origenTectonico, alcanceRegional, estadoValidado
        );

        // Añadir Cambios de Estado para Evento 1
        CambioDeEstado ce1_e1 = new CambioDeEstado(fechaHoraOcurrencia1.minusMinutes(10), fechaHoraOcurrencia1.plusMinutes(5), estadoPendiente, emp1);
        CambioDeEstado ce2_e1 = new CambioDeEstado(fechaHoraOcurrencia1.plusMinutes(5), fechaHoraOcurrencia1.plusMinutes(20), estadoEnRevision, emp2);
        CambioDeEstado ce3_e1 = new CambioDeEstado(fechaHoraOcurrencia1.plusMinutes(20), fechaHoraFin1, estadoValidado, emp3);
        evento1.addCambioDeEstado(ce1_e1);
        evento1.addCambioDeEstado(ce2_e1);
        evento1.addCambioDeEstado(ce3_e1);


        // Añadir Series Temporales y Muestras Sísmicas para Evento 1
        SerieTemporal st1_e1 = new SerieTemporal("Alta", LocalDateTime.of(2024, 5, 20, 14, 25), LocalDateTime.of(2024, 5, 20, 15, 0), 10.0, estadoAlertaSerie);
        st1_e1.crearMuestraSismica(LocalDateTime.of(2024, 5, 20, 14, 30));
        st1_e1.getMuestrasSismicas().get(0).crearDetalleMuestra(6.2, tipoMagnitud);
        st1_e1.getMuestrasSismicas().get(0).crearDetalleMuestra(120.5, tipoProfundidad);
        sismografo1.addSerieTemporal(st1_e1); // Asociar serie temporal a un sismógrafo
        evento1.addSerieTemporal(st1_e1);

        eventosSismicos.add(evento1);

        // --- Evento Sísmico 2 ---
        LocalDateTime fechaHoraOcurrencia2 = LocalDateTime.of(2024, 5, 18, 8, 0, 0);
        LocalDateTime fechaHoraFin2 = fechaHoraOcurrencia2.plusHours(2);
        EventoSismico evento2 = new EventoSismico(
            fechaHoraFin2, fechaHoraOcurrencia2,
            -20.0, -20.1,
            -70.0, -70.1,
            7.1, "Global", "Profundo", "Tectónico",
            clasifProfundo, origenTectonico, alcanceGlobal, estadoValidado
        );

        CambioDeEstado ce1_e2 = new CambioDeEstado(fechaHoraOcurrencia2.minusMinutes(5), fechaHoraOcurrencia2.plusMinutes(10), estadoPendiente, emp2);
        CambioDeEstado ce2_e2 = new CambioDeEstado(fechaHoraOcurrencia2.plusMinutes(10), fechaHoraFin2, estadoValidado, emp1);
        evento2.addCambioDeEstado(ce1_e2);
        evento2.addCambioDeEstado(ce2_e2);

        SerieTemporal st1_e2 = new SerieTemporal("Crítica", LocalDateTime.of(2024, 5, 18, 7, 50), LocalDateTime.of(2024, 5, 18, 10, 0), 5.0, estadoAlertaSerie);
        st1_e2.crearMuestraSismica(LocalDateTime.of(2024, 5, 18, 8, 15));
        st1_e2.getMuestrasSismicas().get(0).crearDetalleMuestra(7.1, tipoMagnitud);
        st1_e2.getMuestrasSismicas().get(0).crearDetalleMuestra(450.0, tipoProfundidad);
        sismografo2.addSerieTemporal(st1_e2);
        evento2.addSerieTemporal(st1_e2);
        eventosSismicos.add(evento2);

        // --- Evento Sísmico 3 ---
        LocalDateTime fechaHoraOcurrencia3 = LocalDateTime.of(2024, 5, 15, 22, 10, 0);
        LocalDateTime fechaHoraFin3 = fechaHoraOcurrencia3.plusMinutes(45);
        EventoSismico evento3 = new EventoSismico(
            fechaHoraFin3, fechaHoraOcurrencia3,
            -30.0, -30.0,
            -60.0, -60.0,
            4.5, "Local", "Leve", "Antrópico",
            clasifLeve, origenAntropico, alcanceLocal, estadoRechazado
        );
        CambioDeEstado ce1_e3 = new CambioDeEstado(fechaHoraOcurrencia3.minusMinutes(2), fechaHoraFin3, estadoRechazado, emp3);
        evento3.addCambioDeEstado(ce1_e3);
        eventosSismicos.add(evento3);

        // --- Evento Sísmico 4 ---
        LocalDateTime fechaHoraOcurrencia4 = LocalDateTime.of(2024, 5, 10, 11, 0, 0);
        LocalDateTime fechaHoraFin4 = fechaHoraOcurrencia4.plusHours(1);
        EventoSismico evento4 = new EventoSismico(
            fechaHoraFin4, fechaHoraOcurrencia4,
            -40.0, -40.0,
            -70.0, -70.0,
            5.8, "Regional", "Moderado", "Volcánico",
            clasifModerado, origenVolcanico, alcanceRegional, estadoValidado
        );
        CambioDeEstado ce1_e4 = new CambioDeEstado(fechaHoraOcurrencia4.minusMinutes(10), fechaHoraOcurrencia4.plusMinutes(15), estadoPendiente, emp1);
        CambioDeEstado ce2_e4 = new CambioDeEstado(fechaHoraOcurrencia4.plusMinutes(15), fechaHoraFin4, estadoValidado, emp2);
        evento4.addCambioDeEstado(ce1_e4);
        evento4.addCambioDeEstado(ce2_e4);

        SerieTemporal st1_e4 = new SerieTemporal("Media", LocalDateTime.of(2024, 5, 10, 10, 55), LocalDateTime.of(2024, 5, 10, 12, 0), 8.0, estadoNormalSerie);
        st1_e4.crearMuestraSismica(LocalDateTime.of(2024, 5, 10, 11, 30));
        st1_e4.getMuestrasSismicas().get(0).crearDetalleMuestra(5.8, tipoMagnitud);
        st1_e4.getMuestrasSismicas().get(0).crearDetalleMuestra(50.0, tipoProfundidad);
        sismografo3.addSerieTemporal(st1_e4);
        evento4.addSerieTemporal(st1_e4);
        eventosSismicos.add(evento4);

        // --- Evento Sísmico 5 ---
        LocalDateTime fechaHoraOcurrencia5 = LocalDateTime.of(2024, 5, 5, 3, 0, 0);
        LocalDateTime fechaHoraFin5 = fechaHoraOcurrencia5.plusMinutes(30);
        EventoSismico evento5 = new EventoSismico(
            fechaHoraFin5, fechaHoraOcurrencia5,
            -25.0, -25.1,
            -65.0, -65.2,
            3.1, "Local", "Leve", "Tectónico",
            clasifLeve, origenTectonico, alcanceLocal, estadoEnRevision
        );
        CambioDeEstado ce1_e5 = new CambioDeEstado(fechaHoraOcurrencia5.minusMinutes(1), null, estadoEnRevision, emp3); // Cambio de estado actual sin fecha fin
        evento5.addCambioDeEstado(ce1_e5);
        eventosSismicos.add(evento5);

        // --- Evento Sísmico 6 (Nuevo evento pendiente) ---
        LocalDateTime fechaHoraOcurrencia6 = LocalDateTime.of(2025, 5, 21, 16, 45, 0); // Fecha y hora actual
        LocalDateTime fechaHoraFin6 = fechaHoraOcurrencia6.plusMinutes(10); // Un final tentativo
        EventoSismico evento6 = new EventoSismico(
          fechaHoraFin6, fechaHoraOcurrencia6,
          -31.5, -31.6,
          -64.2, -64.3,
          2.9, "Local", "Leve", "Tectónico",
          clasifLeve, origenTectonico, alcanceLocal, estadoPendiente // Asigna estadoPendiente directamente
       );

      // Añadir un CambioDeEstado inicial para reflejar que está pendiente
      // Nota: La fechaFin del CambioDeEstado es null porque es el estado actual.
      CambioDeEstado ce1_e6 = new CambioDeEstado(fechaHoraOcurrencia6.minusSeconds(1), null, estadoPendiente, emp1);
        evento6.addCambioDeEstado(ce1_e6);
        eventosSismicos.add(evento6);

        Sesion sesionActual = new Sesion(LocalDateTime.now(), emp1);

        // 3. Imprimir información de los Eventos Sísmicos creados (esto se puede dejar o quitar si solo quieres la GUI)
        System.out.println("--- Detalles de los Eventos Sísmicos Creados ---\n");
        for (int i = 0; i < eventosSismicos.size(); i++) {
            EventoSismico evento = eventosSismicos.get(i);
            System.out.println("Evento Sísmico " + (i + 1) + ":");
            System.out.println(evento);

            System.out.println("  Cambios de Estado:");
            for (CambioDeEstado ce : evento.getCambiosDeEstado()) {
                System.out.println("    - " + ce);
            }

            System.out.println("  Series Temporales:");
            for (SerieTemporal st : evento.getSeriesTemporales()) {
                System.out.println("    - " + st);
                System.out.println("      Muestras Sísmicas en esta Serie:");
                for (MuestraSismica ms : st.getMuestrasSismicas()) {
                    System.out.println("        - " + ms);
                    System.out.println("          Detalles de la Muestra:");
                    for (DetalleMuestraSismica dms : ms.getDetalles()) {
                        System.out.println("            -> " + dms.getDatos()); // Usamos getDatos() para formato específico
                    }
                }
            }
            System.out.println("\n----------------------------------------------\n");
        }

        // 4. Ejemplos adicionales de uso de algunas instancias base
        System.out.println("--- Ejemplos de instancias base ---\n");
        System.out.println("Empleado 1: " + emp1);
        System.out.println("Estado Validado: " + estadoValidado);
        System.out.println("Tipo de Dato Magnitud: " + tipoMagnitud.getDatos());
        System.out.println("Estación Sismológica 1: " + est1);
        System.out.println("Sismógrafo 1: " + sismografo1);

        System.out.println("\nPlayground finalizado. Lanzando la interfaz gráfica...\n");

        // --- Cargar la instancia de la Pantalla ---
        SwingUtilities.invokeLater(new Runnable() {
            @Override
              public void run() {
                // 1. Crea la instancia de la Pantalla.
                PantallaRegistrarResultadoDeRevisionManual pantallaInstancia = new PantallaRegistrarResultadoDeRevisionManual(null);

                // 2. Crea la instancia del Gestor, pasándole la 'pantallaInstancia' DIRECTAMENTE.
                GestorRegistrarResultadoDeRevisionManual gestorInstancia = new GestorRegistrarResultadoDeRevisionManual( 
                    null, // nombreAlcance
                    null, // nombreClasificacion
                    null, // nombreOrigen
                    null, // fechaHoraRevision
                    pantallaInstancia, // Aquí se inyecta la referencia de la Pantalla en el Gestor (tipo concreto) 
                    eventosSismicos,
                    sesionActual
                );

                // 3. Cierra el ciclo: inyecta la instancia del Gestor en la Pantalla usando el setter.
                pantallaInstancia.setGestor(gestorInstancia);
            }
        });
    }
}

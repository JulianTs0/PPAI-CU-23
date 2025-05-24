package com.mycompany.esismicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    // Declaración de la lista de eventos sísmicos y estados
    public static List<EventoSismico> eventosSismicos = new ArrayList<>();
    public static List<Estado> estados = new ArrayList<>();

    // 1. Crear instancias de clases base (que no dependen de otras en su constructor)
    // Empleados
    public static Empleado
            emp1 = new Empleado("Juan", "Pérez", "Analista Sísmico"),
            emp2 = new Empleado("María", "González", "Técnica de Campo"),
            emp3 = new Empleado("Carlos", "Rodríguez", "Jefe de Observatorio");

    // Estados
    public static Estado
            estadoPendiente = new Estado("EventoSismico", "Pendiente de Revisión"),
            estadoRechazado = new Estado("EventoSismico", "Rechazado"),
            estadoValidado = new Estado("EventoSismico", "Validado"),
            estadoActivoSismografo = new Estado("Sismografo", "Activo"),
            estadoInactivoSismografo = new Estado("Sismografo", "Inactivo"),
            estadoNormalSerie = new Estado("SerieTemporal", "Normal"),
            estadoAlertaSerie = new Estado("SerieTemporal", "Alerta"),
            estadoBloqueadoRevision = new Estado("EventoSismico", "Bloqueado A Revisar"),
            estadoAutoDetectado = new Estado("EventoSismico", "Auto Detectado"),
            estadoAutoConfirmado = new Estado("EventoSismico", "Auto Confirmado"),
            estadoAnulado = new Estado("EventoSismico", "Anulado"),
            estadoPendienteDeCierre = new Estado("EventoSismico", "Pendiente de Cierre"),
            estadoCerrado = new Estado("EventoSismico", "Cerrado");


    // --- BLOQUE STATIC PARA POBLAR LA LISTA 'estados' ---
    static {
        estados.add(estadoPendiente);
        estados.add(estadoRechazado);
        estados.add(estadoValidado);
        estados.add(estadoActivoSismografo);
        estados.add(estadoInactivoSismografo);
        estados.add(estadoNormalSerie);
        estados.add(estadoAlertaSerie);
        estados.add(estadoBloqueadoRevision);
        estados.add(estadoAutoDetectado);
        estados.add(estadoAutoConfirmado);
        estados.add(estadoAnulado);
        estados.add(estadoPendienteDeCierre);
        estados.add(estadoCerrado);
        System.out.println("DataBase: Lista de estados inicializada con " + estados.size() + " elementos.");
    }
    // --- FIN DEL BLOQUE STATIC ---


    // Tipos de Dato para Muestras Sísmicas
    public static TipoDeDato tipoMagnitud = new TipoDeDato("Magnitud", "Richter");
    public static TipoDeDato tipoProfundidad = new TipoDeDato("Profundidad", "km");
    public static TipoDeDato tipoAceleracion = new TipoDeDato("Aceleración", "m/s²");

    // Clasificaciones de Sismo
    public static ClasificacionSismo clasifLeve = new ClasificacionSismo(0.0, 50.0, "Leve");
    public static ClasificacionSismo clasifModerado = new ClasificacionSismo(50.1, 300.0, "Moderado");
    public static ClasificacionSismo clasifProfundo = new ClasificacionSismo(300.1, 700.0, "Profundo");

    // Orígenes de Generación
    public static OrigenDeGeneracion origenTectonico = new OrigenDeGeneracion("Movimiento de placas tectónicas", "Tectónico");
    public static OrigenDeGeneracion origenVolcanico = new OrigenDeGeneracion("Actividad volcánica", "Volcánico");
    public static OrigenDeGeneracion origenAntropico = new OrigenDeGeneracion("Inducido por actividad humana", "Antrópico");

    // Alcances de Sismo
    public static AlcanceSismo alcanceLocal = new AlcanceSismo("Sentido en un área pequeña", "Local");
    public static AlcanceSismo alcanceRegional = new AlcanceSismo("Sentido en una región", "Regional");
    public static AlcanceSismo alcanceGlobal = new AlcanceSismo("Sentido en grandes extensiones", "Global");

    // Estaciones Sismológicas
    public static EstacionSismologica est1 = new EstacionSismologica("EST-001", "DocCert_123", LocalDateTime.of(2023, 1, 15, 10, 0), -31.4167, -64.1833, "Estación Córdoba", "CERT-A-001");
    public static EstacionSismologica est2 = new EstacionSismologica("EST-002", "DocCert_456", LocalDateTime.of(2023, 3, 20, 8, 30), -34.6037, -58.3816, "Estación Buenos Aires", "CERT-B-002");
    public static EstacionSismologica est3 = new EstacionSismologica("EST-003", "DocCert_789", LocalDateTime.of(2022, 11, 5, 14, 45), -24.7828, -65.4107, "Estación Salta", "CERT-C-003");

    // Sismógrafos (asociados a estaciones y estados)
    public static Sismografo sismografo1 = new Sismografo(LocalDateTime.of(2024, 2, 1, 9, 0), "SISM-001", "SN12345", "Sismógrafo Principal Cba", est1, estadoActivoSismografo);
    public static Sismografo sismografo2 = new Sismografo(LocalDateTime.of(2024, 3, 10, 11, 30), "SISM-002", "SN67890", "Sismógrafo Secundario BA", est2, estadoActivoSismografo);
    public static Sismografo sismografo3 = new Sismografo(LocalDateTime.of(2024, 4, 15, 16, 0), "SISM-003", "SN11223", "Sismógrafo Remoto Salta", est3, estadoActivoSismografo);

    // --- Evento Sísmico 1 (Magnitud 6.2 - Auto Confirmado / Pendiente de Cierre) ---
    public static LocalDateTime fechaHoraOcurrencia1 = LocalDateTime.of(2024, 5, 20, 14, 30, 0);

    public static EventoSismico evento1 = new EventoSismico(
            fechaHoraOcurrencia1.plusHours(1), fechaHoraOcurrencia1,
            -33.5, -33.4, -69.5, -69.4,
            6.2, // MODIFICADO: Eliminados "Regional", "Moderado", "Tectónico"
            clasifModerado, origenTectonico, alcanceRegional, estadoPendienteDeCierre
    );

    static{
        // Simulamos un flujo: Auto Confirmado -> Validado -> Pendiente de Cierre
        evento1.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia1.minusMinutes(1), fechaHoraOcurrencia1.plusMinutes(1), estadoAutoConfirmado, emp1));
        evento1.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia1.plusMinutes(1), fechaHoraOcurrencia1.plusMinutes(35), estadoValidado, emp2));
        evento1.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia1.plusMinutes(35), null, estadoPendienteDeCierre, emp3));
        evento1.setEstadoActual(estadoPendienteDeCierre);
    }

    // Añadir Series Temporales y Muestras Sísmicas para Evento 1
    public static SerieTemporal
            st1_e1 = new SerieTemporal("Alta", LocalDateTime.of(2024, 5, 20, 14, 25), LocalDateTime.of(2024, 5, 20, 15, 0), 10.0, estadoAlertaSerie);
    static {
        st1_e1.crearMuestraSismica(LocalDateTime.of(2024, 5, 20, 14, 30));
        st1_e1.getMuestrasSismicas().get(0).crearDetalleMuestra(6.2, tipoMagnitud);
        st1_e1.getMuestrasSismicas().get(0).crearDetalleMuestra(120.5, tipoProfundidad);
        sismografo1.addSerieTemporal(st1_e1);
        evento1.addSerieTemporal(st1_e1);
        eventosSismicos.add(evento1);
    }

    // --- Evento Sísmico 2 (Magnitud 7.1 - Auto Confirmado / Cerrado) ---
    public static LocalDateTime fechaHoraOcurrencia2 = LocalDateTime.of(2024, 5, 18, 8, 0, 0);

    public static EventoSismico evento2 = new EventoSismico(
            fechaHoraOcurrencia2.plusHours(2), fechaHoraOcurrencia2,
            -20.0, -20.1, -70.0, -70.1,
            7.1, // MODIFICADO: Eliminados "Global", "Profundo", "Tectónico"
            clasifProfundo, origenTectonico, alcanceGlobal, estadoCerrado
    );

    static{
        // Simulamos un flujo: Auto Confirmado -> Validado -> Pendiente de Cierre -> Cerrado
        evento2.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia2.minusMinutes(5), fechaHoraOcurrencia2.plusMinutes(10), estadoAutoConfirmado, emp2));
        evento2.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia2.plusMinutes(10), fechaHoraOcurrencia2.plusHours(1), estadoValidado, emp1));
        evento2.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia2.plusHours(1), fechaHoraOcurrencia2.plusHours(1).plusMinutes(15), estadoPendienteDeCierre, emp1));
        evento2.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia2.plusHours(1).plusMinutes(15), null, estadoCerrado, emp3));
        evento2.setEstadoActual(estadoCerrado);
    }
    // Añadir Series Temporales y Muestras Sísmicas para Evento
    public static SerieTemporal
            st1_e2 = new SerieTemporal("Crítica", LocalDateTime.of(2024, 5, 18, 7, 50), LocalDateTime.of(2024, 5, 18, 10, 0), 5.0, estadoAlertaSerie);
    static{
        st1_e2.crearMuestraSismica(LocalDateTime.of(2024, 5, 18, 8, 15));
        st1_e2.getMuestrasSismicas().get(0).crearDetalleMuestra(7.1, tipoMagnitud);
        st1_e2.getMuestrasSismicas().get(0).crearDetalleMuestra(450.0, tipoProfundidad);
        sismografo2.addSerieTemporal(st1_e2);
        evento2.addSerieTemporal(st1_e2);
        eventosSismicos.add(evento2);
    }

    // --- Evento Sísmico 3 (Magnitud 4.5 - Auto Confirmado / Rechazado) ---
    public static LocalDateTime fechaHoraOcurrencia3 = LocalDateTime.of(2024, 5, 15, 22, 10, 0);

    public static EventoSismico evento3 = new EventoSismico(
            fechaHoraOcurrencia3.plusMinutes(45), fechaHoraOcurrencia3,
            -30.0, -30.0, -60.0, -60.0,
            4.5, // MODIFICADO: Eliminados "Local", "Leve", "Antrópico"
            clasifLeve, origenAntropico, alcanceLocal, estadoRechazado
    );
    static{
        // Simulamos un flujo: Auto Confirmado -> Bloqueado A Revisar -> Rechazado
        evento3.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia3.minusMinutes(2), fechaHoraOcurrencia3.plusMinutes(5), estadoAutoConfirmado, emp3));
        evento3.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia3.plusMinutes(5), fechaHoraOcurrencia3.plusMinutes(15), estadoBloqueadoRevision, emp1));
        evento3.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia3.plusMinutes(15), null, estadoRechazado, emp2));
        evento3.setEstadoActual(estadoRechazado);
        eventosSismicos.add(evento3);
    }

    // --- Evento Sísmico 4 (Magnitud 5.8 - Auto Confirmado / Validado) ---
    public static LocalDateTime fechaHoraOcurrencia4 = LocalDateTime.of(2024, 5, 10, 11, 0, 0);

    public static EventoSismico evento4 = new EventoSismico(
            fechaHoraOcurrencia4.plusHours(1), fechaHoraOcurrencia4,
            -40.0, -40.0, -70.0, -70.0,
            5.8, // MODIFICADO: Eliminados "Regional", "Moderado", "Volcánico"
            clasifModerado, origenVolcanico, alcanceRegional, estadoValidado
    );

    static{
        // Simulamos un flujo: Auto Confirmado -> Bloqueado A Revisar -> Validado
        evento4.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia4.minusMinutes(10), fechaHoraOcurrencia4.plusMinutes(5), estadoAutoConfirmado, emp1));
        evento4.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia4.plusMinutes(5), fechaHoraOcurrencia4.plusMinutes(20), estadoBloqueadoRevision, emp2));
        evento4.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia4.plusMinutes(20), null, estadoValidado, emp3));
        evento4.setEstadoActual(estadoValidado);
    }
    public static SerieTemporal
            st1_e4 = new SerieTemporal("Media", LocalDateTime.of(2024, 5, 10, 10, 55), LocalDateTime.of(2024, 5, 10, 12, 0), 8.0, estadoNormalSerie);
    static{
        st1_e4.crearMuestraSismica(LocalDateTime.of(2024, 5, 10, 11, 30));
        st1_e4.getMuestrasSismicas().get(0).crearDetalleMuestra(5.8, tipoMagnitud);
        st1_e4.getMuestrasSismicas().get(0).crearDetalleMuestra(50.0, tipoProfundidad);
        sismografo3.addSerieTemporal(st1_e4);
        evento4.addSerieTemporal(st1_e4);
        eventosSismicos.add(evento4);
    }

    // --- Evento Sísmico 5 (Magnitud 3.1 - Auto Detectado / Bloqueado A Revisar) ---
    public static LocalDateTime fechaHoraOcurrencia5 = LocalDateTime.of(2024, 5, 5, 3, 0, 0);

    public static EventoSismico evento5 = new EventoSismico(
            fechaHoraOcurrencia5.plusMinutes(30), fechaHoraOcurrencia5,
            -25.0, -25.1, -65.0, -65.2,
            3.1, // MODIFICADO: Eliminados "Local", "Leve", "Tectónico"
            clasifLeve, origenTectonico, alcanceLocal, estadoBloqueadoRevision
    );
    static{
        // Simulamos un flujo: Auto Detectado -> Pendiente de Revisión -> Bloqueado A Revisar
        evento5.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia5.minusMinutes(1), fechaHoraOcurrencia5.plusMinutes(2), estadoAutoDetectado, null)); // Sin empleado inicial
        evento5.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia5.plusMinutes(2), fechaHoraOcurrencia5.plusMinutes(8), estadoPendiente, emp3)); // Analista lo toma
        evento5.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia5.plusMinutes(8), null, estadoBloqueadoRevision, emp3)); // Estado actual
        eventosSismicos.add(evento5);
    }

    // --- Evento Sísmico 6 (Magnitud 2.9 - Auto Detectado / Pendiente de Revisión) ---
    public static LocalDateTime fechaHoraOcurrencia6 = LocalDateTime.of(2025, 5, 21, 16, 45, 0);

    public static EventoSismico evento6 = new EventoSismico(
            fechaHoraOcurrencia6.plusMinutes(10), fechaHoraOcurrencia6,
            -71.5, -11.6, -61.2, -34.3,
            3.5, // MODIFICADO: Eliminados "Local", "Leve", "Tectónico"
            clasifModerado, origenVolcanico, alcanceRegional, estadoPendiente
    );

    static{
        // Simulamos un flujo: Auto Detectado -> Pendiente de Revisión
        evento6.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia6.minusSeconds(1), fechaHoraOcurrencia6.plusMinutes(2), estadoAutoDetectado, null));
        evento6.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia6.plusMinutes(2), null, estadoPendiente, emp1));
        eventosSismicos.add(evento6);
    }

    // --- Evento Sísmico 7 (Magnitud 2.9 - Auto Detectado / Anulado) ---
    public static LocalDateTime fechaHoraOcurrencia7 = LocalDateTime.of(2024, 2, 13, 2, 45, 0);

    public static EventoSismico evento7 = new EventoSismico(
            fechaHoraOcurrencia7.plusMinutes(10), fechaHoraOcurrencia7,
            -31.5, -31.6, -64.2, -64.3,
            2.9, // MODIFICADO: Eliminados "Local", "Leve", "Tectónico"
            clasifLeve, origenTectonico, alcanceLocal, estadoAnulado
    );

    static{
        // Simulamos un flujo: Auto Detectado -> Pendiente de Revisión -> Anulado
        evento7.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia7.minusSeconds(5), fechaHoraOcurrencia7.plusMinutes(1), estadoAutoDetectado, null));
        evento7.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia7.plusMinutes(1), fechaHoraOcurrencia7.plusMinutes(11), estadoPendiente, emp2));
        evento7.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia7.plusMinutes(11), null, estadoAnulado, null));
        eventosSismicos.add(evento7);
    }

    // --- Evento Sísmico 8 (Magnitud 2.9 - Auto Detectado / Pendiente de Revisión) ---
    public static LocalDateTime fechaHoraOcurrencia8 = LocalDateTime.of(2025, 1, 1, 2, 0, 0);

    public static EventoSismico evento8 = new EventoSismico(
            fechaHoraOcurrencia8.plusMinutes(10), fechaHoraOcurrencia8,
            -31.5, -31.6,
            -64.2, -64.3,
            2.9, // MODIFICADO: Eliminados "Local", "Leve", "Tectónico"
            clasifLeve, origenTectonico, alcanceLocal, estadoPendiente
    );

    static{
        // Simulamos un flujo: Auto Detectado -> Pendiente de Revisión
        evento8.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia8.minusSeconds(5), fechaHoraOcurrencia8.plusMinutes(1), estadoAutoDetectado, null));
        evento8.addCambioDeEstado(new CambioDeEstado(fechaHoraOcurrencia8.plusMinutes(1), null, estadoPendiente, emp3));
        eventosSismicos.add(evento8);
    }

    public static Sesion sesionActual = new Sesion(LocalDateTime.now(), emp1);

}
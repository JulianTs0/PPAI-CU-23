package com.mycompany.esismicos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    
    // Declaración de la lista de eventos sísmicos
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
        estadoEnRevision = new Estado("EventoSismico", "En Revisión"),
        estadoRechazado = new Estado("EventoSismico", "Rechazado"),
        estadoValidado = new Estado("EventoSismico", "Validado"),
        estadoActivoSismografo = new Estado("Sismografo", "Activo"),
        estadoInactivoSismografo = new Estado("Sismografo", "Inactivo"),
        estadoNormalSerie = new Estado("SerieTemporal", "Normal"),
        estadoAlertaSerie = new Estado("SerieTemporal", "Alerta"),
        estadoBloqueadoRevision = new Estado("EventoSismico", "Bloqueado A Revisar");

    // --- NUEVO BLOQUE STATIC PARA POBLAR LA LISTA 'estados' ---
    static {
        estados.add(estadoPendiente);
        estados.add(estadoEnRevision);
        estados.add(estadoRechazado);
        estados.add(estadoValidado);
        estados.add(estadoActivoSismografo);
        estados.add(estadoInactivoSismografo);
        estados.add(estadoNormalSerie);
        estados.add(estadoAlertaSerie);
        estados.add(estadoBloqueadoRevision);
        System.out.println("DataBase: Lista de estados inicializada con " + estados.size() + " elementos.");
    }
    // --- FIN DEL NUEVO BLOQUE STATIC ---


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

    //  -   -   -   -   -   -   -   -   -   -   -

    // 2. Creación de 5 Eventos Sísmicos con sus relaciones completas


    // --- Evento Sísmico 1 ---
    // Fecha y hora del evento
    public static LocalDateTime fechaHoraOcurrencia1 = LocalDateTime.of(2024, 5, 20, 14, 30, 0);
    public static LocalDateTime fechaHoraFin1 = fechaHoraOcurrencia1.plusHours(1);

    public static EventoSismico evento1 = new EventoSismico(
        fechaHoraFin1, fechaHoraOcurrencia1,
        -33.5, -33.4, // Latitud Hipocentro/Epicentro
        -69.5, -69.4, // Longitud Hipocentro/Epicentro
        6.2, "Regional", "Moderado", "Tectónico",
        clasifModerado, origenTectonico, alcanceRegional, estadoValidado
    );
    

    // Añadir Cambios de Estado para Evento 1
    public static CambioDeEstado ce1_e1 = new CambioDeEstado(fechaHoraOcurrencia1.minusMinutes(10), fechaHoraOcurrencia1.plusMinutes(5), estadoPendiente, emp1);
    public static CambioDeEstado ce2_e1 = new CambioDeEstado(fechaHoraOcurrencia1.plusMinutes(5), fechaHoraOcurrencia1.plusMinutes(20), estadoEnRevision, emp2);
    public static CambioDeEstado ce3_e1 = new CambioDeEstado(fechaHoraOcurrencia1.plusMinutes(20), fechaHoraFin1, estadoValidado, emp3);
    static{
        evento1.addCambioDeEstado(ce1_e1);
        evento1.addCambioDeEstado(ce2_e1);
        evento1.addCambioDeEstado(ce3_e1);
    }
    

    
    // Añadir Series Temporales y Muestras Sísmicas para Evento 1
    public static SerieTemporal 
        st1_e1 = new SerieTemporal("Alta", LocalDateTime.of(2024, 5, 20, 14, 25), LocalDateTime.of(2024, 5, 20, 15, 0), 10.0, estadoAlertaSerie);
    static {
        st1_e1.crearMuestraSismica(LocalDateTime.of(2024, 5, 20, 14, 30));
        st1_e1.getMuestrasSismicas().get(0).crearDetalleMuestra(6.2, tipoMagnitud);
        st1_e1.getMuestrasSismicas().get(0).crearDetalleMuestra(120.5, tipoProfundidad);
        sismografo1.addSerieTemporal(st1_e1); // Asociar serie temporal a un sismógrafo
        evento1.addSerieTemporal(st1_e1);

        eventosSismicos.add(evento1);
    }
    // --- Evento Sísmico 2 ---
    public static LocalDateTime 
        fechaHoraOcurrencia2 = LocalDateTime.of(2024, 5, 18, 8, 0, 0),
        fechaHoraFin2 = fechaHoraOcurrencia2.plusHours(2);

    public static EventoSismico evento2 = new EventoSismico(
        fechaHoraFin2, fechaHoraOcurrencia2,
        -20.0, -20.1,
        -70.0, -70.1,
        7.1, "Global", "Profundo", "Tectónico",
        clasifProfundo, origenTectonico, alcanceGlobal, estadoValidado
    );

    // Añadir Cambios de Estado para Evento 2
    public static CambioDeEstado 
        ce1_e2 = new CambioDeEstado(fechaHoraOcurrencia2.minusMinutes(5), fechaHoraOcurrencia2.plusMinutes(10), estadoPendiente, emp2),
        ce2_e2 = new CambioDeEstado(fechaHoraOcurrencia2.plusMinutes(10), fechaHoraFin2, estadoValidado, emp1);
    static{
        evento2.addCambioDeEstado(ce1_e2);
        evento2.addCambioDeEstado(ce2_e2);
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
    // --- Evento Sísmico 3 ---
    public static LocalDateTime 
        fechaHoraOcurrencia3 = LocalDateTime.of(2024, 5, 15, 22, 10, 0),
        fechaHoraFin3 = fechaHoraOcurrencia3.plusMinutes(45);
    
    public static EventoSismico evento3 = new EventoSismico(
        fechaHoraFin3, fechaHoraOcurrencia3,
        -30.0, -30.0,
        -60.0, -60.0,
        4.5, "Local", "Leve", "Antrópico",
        clasifLeve, origenAntropico, alcanceLocal, estadoRechazado
    );
    public static CambioDeEstado 
        ce1_e3 = new CambioDeEstado(fechaHoraOcurrencia3.minusMinutes(2), fechaHoraFin3, estadoRechazado, emp3);
    static{
        evento3.addCambioDeEstado(ce1_e3);
        eventosSismicos.add(evento3);
    }
    // --- Evento Sísmico 4 ---
    public static LocalDateTime 
        fechaHoraOcurrencia4 = LocalDateTime.of(2024, 5, 10, 11, 0, 0),
        fechaHoraFin4 = fechaHoraOcurrencia4.plusHours(1);

    public static EventoSismico evento4 = new EventoSismico(
        fechaHoraFin4, fechaHoraOcurrencia4,
        -40.0, -40.0,
        -70.0, -70.0,
        5.8, "Regional", "Moderado", "Volcánico",
        clasifModerado, origenVolcanico, alcanceRegional, estadoValidado
    );

    public static CambioDeEstado 
        ce1_e4 = new CambioDeEstado(fechaHoraOcurrencia4.minusMinutes(10), fechaHoraOcurrencia4.plusMinutes(15), estadoPendiente, emp1), 
        ce2_e4 = new CambioDeEstado(fechaHoraOcurrencia4.plusMinutes(15), fechaHoraFin4, estadoValidado, emp2);
    static{
        evento4.addCambioDeEstado(ce1_e4);
        evento4.addCambioDeEstado(ce2_e4);
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
    // --- Evento Sísmico 5 ---
    public static LocalDateTime 
        fechaHoraOcurrencia5 = LocalDateTime.of(2024, 5, 5, 3, 0, 0),
        fechaHoraFin5 = fechaHoraOcurrencia5.plusMinutes(30);

    public static EventoSismico evento5 = new EventoSismico(
        fechaHoraFin5, fechaHoraOcurrencia5,
        -25.0, -25.1,
        -65.0, -65.2,
        3.1, "Local", "Leve", "Tectónico",
        clasifLeve, origenTectonico, alcanceLocal, estadoEnRevision
    );
    public static CambioDeEstado 
        ce1_e5 = new CambioDeEstado(fechaHoraOcurrencia5.minusMinutes(1), null, estadoEnRevision, emp3); // Cambio de estado actual sin fecha fin
    static{
        evento5.addCambioDeEstado(ce1_e5);
        eventosSismicos.add(evento5);
    }
    // --- Evento Sísmico 6 (Nuevo evento pendiente) ---
    public static LocalDateTime 
        fechaHoraOcurrencia6 = LocalDateTime.of(2025, 5, 21, 16, 45, 0), // Fecha y hora actual
        fechaHoraFin6 = fechaHoraOcurrencia6.plusMinutes(10); // Un final tentativo

    public static EventoSismico evento6 = new EventoSismico(
        fechaHoraFin6, fechaHoraOcurrencia6,
        -31.5, -31.6,
        -64.2, -64.3,
        2.9, "Local", "Leve", "Tectónico",
        clasifLeve, origenTectonico, alcanceLocal, estadoPendiente // Asigna estadoPendiente directamente
    );

    // Añadir un CambioDeEstado inicial para reflejar que está pendiente
    // Nota: La fechaFin del CambioDeEstado es null porque es el estado actual.
    public static CambioDeEstado 
        ce1_e6 = new CambioDeEstado(fechaHoraOcurrencia6.minusSeconds(1), null, estadoPendiente, emp1);
    static{
        evento6.addCambioDeEstado(ce1_e6);
        eventosSismicos.add(evento6);
    }

    // --- Evento Sísmico 7 (Nuevo evento pendiente) ---
    public static LocalDateTime
            fechaHoraOcurrencia7 = LocalDateTime.of(2024, 2, 13, 2, 45, 0), // Fecha y hora actual
            fechaHoraFin7 = fechaHoraOcurrencia6.plusMinutes(10); // Un final tentativo

    public static EventoSismico evento7 = new EventoSismico(
            fechaHoraFin7, fechaHoraOcurrencia7,
            -31.5, -31.6,
            -64.2, -64.3,
            2.9, "Local", "Leve", "Tectónico",
            clasifLeve, origenTectonico, alcanceLocal, estadoPendiente // Asigna estadoPendiente directamente
    );

    // Añadir un CambioDeEstado inicial para reflejar que está pendiente
    // Nota: La fechaFin del CambioDeEstado es null porque es el estado actual.
    public static CambioDeEstado
            ce1_e7 = new CambioDeEstado(fechaHoraOcurrencia6.minusSeconds(5), null, estadoPendiente, emp2);
    static{
        evento6.addCambioDeEstado(ce1_e7);
        eventosSismicos.add(evento7);
    }

    // --- Evento Sísmico 8 (Nuevo evento pendiente) ---
    public static LocalDateTime
            fechaHoraOcurrencia8 = LocalDateTime.of(2025, 1, 1, 2, 0, 0), // Fecha y hora actual
            fechaHoraFin8 = fechaHoraOcurrencia7.plusMinutes(10); // Un final tentativo

    public static EventoSismico evento8 = new EventoSismico(
            fechaHoraFin8, fechaHoraOcurrencia8,
            -31.5, -31.6,
            -64.2, -64.3,
            2.9, "Local", "Leve", "Tectónico",
            clasifLeve, origenTectonico, alcanceLocal, estadoPendiente // Asigna estadoPendiente directamente
    );

    // Añadir un CambioDeEstado inicial para reflejar que está pendiente
    // Nota: La fechaFin del CambioDeEstado es null porque es el estado actual.
    public static CambioDeEstado
            ce1_e8 = new CambioDeEstado(fechaHoraOcurrencia7.minusSeconds(5), null, estadoPendiente, emp3);
    static{
        evento6.addCambioDeEstado(ce1_e8);
        eventosSismicos.add(evento8);
    }

    public static Sesion sesionActual = new Sesion(LocalDateTime.now(), emp1);

}

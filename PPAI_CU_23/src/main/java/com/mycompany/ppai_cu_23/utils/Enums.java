package com.mycompany.ppai_cu_23.utils;

import java.util.Arrays;

public class Enums {

    // Constantes

    public static enum nombresEstado {

        Auto_Detectado,
        Bloqueado_En_Revision,
        Pendiente_De_Revision,
        Rechazado,
        Transmitida,
        Disponible

    }

    public static enum nombresAmbito {
        Evento_Sismico,
        Serie_Temporal,
        Sismografo
    }

    public static enum nombresDenominacion {
        Velocidad_De_Onda,
        Frecuencia_De_Onda,
        Longitud_De_Onda,
    }

    public static enum nombresAlcanceSismo {
        Local,
        Regional,
        Global
    }

    public static enum nombresOrigenDeGeneracion {
        Tectónico,
        Volcánico,
        Antrópico
    }

    // COLUMNAS FRONTEND

    public static String[] nombresAccion = {
        "Confirmar evento",
        "Rechazar evento",
        "Solicitar revisión a experto"
    };

    public static String[] columnasTablaAutoDetectados = {
        "FechaHora Ocurrencia",
        "Ubicacion Epicentro",
        "Ubicacion Hipocentro",
        "Valor de Magnitud"
    };

    // CARGAR NOMBRES

    public static String[] cargarNombresAlcanceSismo(){
        return Arrays.stream(Enums.nombresAlcanceSismo.values())
            .map(Enum::name)
            .toArray(String[]::new);
    }

    public static String[] cargarNombresOrigenDeGeneracion(){
        return Arrays.stream(Enums.nombresOrigenDeGeneracion.values())
            .map(Enum::name)
            .toArray(String[]::new);
    }

}

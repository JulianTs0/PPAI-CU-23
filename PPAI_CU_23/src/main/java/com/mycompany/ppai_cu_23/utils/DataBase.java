package com.mycompany.ppai_cu_23.utils;

import com.mycompany.ppai_cu_23.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {
    
    //CONSULTAS A BASE DE DATOS
    
    public static TipoDeDato[] cargarTiposDeDato(){
       return tiposDeDato.toArray(new TipoDeDato[0]);
    }
    
    public static Sismografo[] cargarSismografos(){
        return sismografos.toArray(new Sismografo[0]);
    }
    
    public static EventoSismico[] cargarEventosSismicos(){
        return eventosSismicos.toArray(new EventoSismico[0]);
    }
    
    public static Estado[] cargarEstados(){
        return estados.toArray(Estado[]::new);
    }
    
    public static Sesion cargarSesionActual(){
        return new Sesion(LocalDateTime.now(),null,usuarios.get(2));
    }
    
    //nombres
    public static String[] cargarNombresAlcanceSismo(){
        return Arrays.stream(nombresAlcanceSismo.values()).map(Enum::name).toArray(String[]::new);
    }
    
    public static String[] cargarNombresOrigenDeGeneracion(){
        return Arrays.stream(nombresOrigenDeGeneracion.values()).map(Enum::name).toArray(String[]::new);
    }
     
    //NOMBRES
    public static enum nombresEstado {
        //EventoSismico
        Auto_Detectado,
        Auto_Confirmado,
        Bloqueado_En_Revision,
        Rechazado,
        Validado,
        Pendiente_de_Cierre,
        Cerrado,
        //Sismografo
        Activo,
        Inactivo,
        //SerieTemporal
        Normal,
        Alerta,
        
    }
    
    public static enum nombresAmbito {
        Evento_Sismico,
        Sismografo,
        SerieTemporal
    }
    
    public static enum nombresDenominacion {
        Velocidad_De_Onda,
        Frecuencia_De_Onda,
        Longitud_De_Onda,
    }
    
    public static enum nombresUnidadMedida {
        km_s,
        Hz,
        m
    }
    
    public static String[] nombresAccion = {
        "Confirmar evento",
        "Rechazar evento",
        "Solicitar revisión a experto"
    };
    
    
    
    // COLUMNAS TABLA tbl_eventosAutoDetectados
    public static String[] columnasTablaAutoDetectados = {
        "FechaHora Ocurrencia",
        "Ubicacion Epicentro",
        "Ubicacion Hipocentro",
        "Valor de Magnitud"
    };
    
    // [DATA BASE] EMPLEADOS  
    static List<Empleado> empleados = new ArrayList<>(List.of(
        new Empleado("Juan", "Pérez"),
        new Empleado("María", "González"),
        new Empleado("Carlos", "Rodríguez")
    ));    

    // [DATA BASE] USUARIOS 
    static List<Usuario> usuarios = new ArrayList<>(List.of(
        new Usuario("juancete", "123456", empleados.get(0)),
        new Usuario("ks", "123456", empleados.get(1)),
        new Usuario("vegetta", "123456", empleados.get(2))
    ));
    
    // [DATA BASE] TIPOS DE DATO
    static List<TipoDeDato> tiposDeDato = new ArrayList<>(List.of(
        new TipoDeDato(
            nombresDenominacion.Velocidad_De_Onda.name(),
            nombresUnidadMedida.km_s.name()
        ),
        new TipoDeDato(
            nombresDenominacion.Frecuencia_De_Onda.name(),
            nombresUnidadMedida.Hz.name()
        ),
        new TipoDeDato(
            nombresDenominacion.Longitud_De_Onda.name(),
            nombresUnidadMedida.m.name())
    
    ));

    // [DATA BASE] ESTADOS
    static List<Estado> estados = new ArrayList<>(List.of(
        new Estado(nombresAmbito.Evento_Sismico.name(), nombresEstado.Auto_Detectado.name()),
        new Estado(nombresAmbito.Evento_Sismico.name(), nombresEstado.Auto_Confirmado.name()),
        new Estado(nombresAmbito.Evento_Sismico.name(), nombresEstado.Bloqueado_En_Revision.name()),
        new Estado(nombresAmbito.Evento_Sismico.name(), nombresEstado.Rechazado.name()),
        new Estado(nombresAmbito.Evento_Sismico.name(), nombresEstado.Validado.name()),
        new Estado(nombresAmbito.Evento_Sismico.name(), nombresEstado.Pendiente_de_Cierre.name()),
        new Estado(nombresAmbito.Evento_Sismico.name(), nombresEstado.Cerrado.name()),
        //Sismografo
        new Estado(nombresAmbito.Sismografo.name(), nombresEstado.Activo.name()),
        new Estado(nombresAmbito.Sismografo.name(), nombresEstado.Inactivo.name()),
        //SerieTemporal
        new Estado(nombresAmbito.SerieTemporal.name(), nombresEstado.Normal.name()),
        new Estado(nombresAmbito.SerieTemporal.name(), nombresEstado.Alerta.name())
    ));
    
    // [DATA BASE] ALCANCES
    public static enum nombresAlcanceSismo {
        Local,
        Regional,
        Global
    }
    
    static List<AlcanceSismo> alcances = new ArrayList<>(List.of(
        new AlcanceSismo(nombresAlcanceSismo.Local.name(),null),
        new AlcanceSismo(nombresAlcanceSismo.Regional.name(),null),
        new AlcanceSismo(nombresAlcanceSismo.Global.name(),null)
    ));
    
    // [DATA BASE] CLASIFICACIONES
    public static enum nombresClasificacionSismo {
        Leve,
        Moderado,
        Profundo
    }
    
    static List<ClasificacionSismo> clasificaciones = new ArrayList<>(List.of(
        new ClasificacionSismo(nombresClasificacionSismo.Leve.name()),
        new ClasificacionSismo(nombresClasificacionSismo.Moderado.name()),
        new ClasificacionSismo(nombresClasificacionSismo.Profundo.name())
    ));
    
    // [DATA BASE] ORIGENES
    public static enum nombresOrigenDeGeneracion {
        Tectónico,
        Volcánico,
        Antrópico
    }
    
    static List<OrigenDeGeneracion> origenes = new ArrayList<>(List.of(
        new OrigenDeGeneracion(nombresOrigenDeGeneracion.Tectónico.name()),
        new OrigenDeGeneracion(nombresOrigenDeGeneracion.Volcánico.name()),
        new OrigenDeGeneracion(nombresOrigenDeGeneracion.Antrópico.name())
    ));
    
    // [DATA BASE] MAGNITUDES RITCHER
    static List<MagnitudRichter> magnitudes = new ArrayList<>(List.of(
        new MagnitudRichter(1),
        new MagnitudRichter(2),
        new MagnitudRichter(3),
        new MagnitudRichter(4),
        new MagnitudRichter(5),
        new MagnitudRichter(6),
        new MagnitudRichter(7)
    ));

    // [DATA BASE] SERIES TEMPORALES
    static List<SerieTemporal> seriesTemporales = new ArrayList<>(List.of(
        new SerieTemporal(
            new ArrayList<>(List.of(
                new MuestraSismica(
                    LocalDateTime.of(2024, 5, 20, 14, 30, 1),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(6.2f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(20.0f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(500f, tiposDeDato.get(2))
                    ))
                ),
                new MuestraSismica(
                    LocalDateTime.of(2024, 5, 20, 14, 32, 12),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(6.3f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(22.3f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(400f, tiposDeDato.get(2))
                    ))
                )
            ))
        ),
        new SerieTemporal(
            new ArrayList<>(List.of(
                new MuestraSismica(
                    LocalDateTime.of(2024, 5, 18, 14, 30, 1),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(8.7f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(35.0f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(700f, tiposDeDato.get(2))
                    ))
                ),
                new MuestraSismica(
                    LocalDateTime.of(2024, 5, 18, 14, 33, 5),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(8.2f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(32f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(675f, tiposDeDato.get(2))
                    ))
                )
            ))
        ),
        new SerieTemporal(
            new ArrayList<>(List.of(
                new MuestraSismica(
                    LocalDateTime.of(2024, 5, 25, 14, 30, 1),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(10.3f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(25f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(340f, tiposDeDato.get(2))
                    ))
                ),
                new MuestraSismica(
                    LocalDateTime.of(2024, 5, 25, 14, 33, 1),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(9.2f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(28.2f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(390f, tiposDeDato.get(2))
                    ))
                )
            ))
        ),
        new SerieTemporal(
            new ArrayList<>(List.of(
                new MuestraSismica(
                    LocalDateTime.of(2023, 8, 12, 0, 0, 1),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(9.2f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(30f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(540f, tiposDeDato.get(2))
                    ))
                ),
                new MuestraSismica(
                    LocalDateTime.of(2023, 8, 12, 0, 5, 1),
                    new ArrayList<>(List.of(
                        new DetalleMuestraSismica(9.3f, tiposDeDato.get(0)),
                        new DetalleMuestraSismica(28.9f, tiposDeDato.get(1)),
                        new DetalleMuestraSismica(399f, tiposDeDato.get(2))
                    ))
                )
            ))
        )
    ));

    // [DATA BASE] SISMOGRAFOS
    static List<Sismografo> sismografos = new ArrayList<>(List.of(
        new Sismografo(
            new EstacionSismologica("Estacion Salta"),
            new ArrayList<>(List.of(seriesTemporales.get(0)))
        ),
        new Sismografo(
            new EstacionSismologica("Estacion Cordoba"),
            new ArrayList<>(List.of(seriesTemporales.get(1)))
        ),
        new Sismografo(
            new EstacionSismologica("Estacion Buenos aires"),
            new ArrayList<>(List.of(seriesTemporales.get(2),seriesTemporales.get(3)))
        )
    ));
    
    // [DATA BASE] EVENTOS SISMICOS
    public static List<EventoSismico> eventosSismicos = new ArrayList<>(List.of(new EventoSismico(
            LocalDateTime.of(2024, 5, 20, 14, 30, 1),
            -30.5f, -31.2f,
            -64.5f, -59.4f,
            2.5f,
            clasificaciones.get(0),
            origenes.get(0),
            alcances.get(0),
            estados.get(0),
            magnitudes.get(2), 
            new ArrayList<>(List.of(
                new CambioDeEstado(
                    LocalDateTime.of(2024, 5, 20, 14, 30, 1),
                    null,
                    estados.get(0),
                    empleados.get(0)
                )
            )),
            new ArrayList<>(List.of( seriesTemporales.get(0) ))
        ),
        new EventoSismico(
            LocalDateTime.of(2024, 5, 18, 14, 30, 1),
            21.9f, 23.4f, 
            37.1f, 31.9f,
            1.8f,
            clasificaciones.get(1),
            origenes.get(1),
            alcances.get(1),
            estados.get(0),
            magnitudes.get(1), 
            new ArrayList<>(List.of(
                new CambioDeEstado(
                    LocalDateTime.of(2024, 5, 18, 14, 30, 1),
                    null,
                    estados.get(0),
                    empleados.get(1)
                )
            )),
            new ArrayList<>(List.of( seriesTemporales.get(1) ))
        ),
        new EventoSismico(
            LocalDateTime.of(2024, 5, 25, 14, 30, 1),
            -103.6f, -104.9f, 
            -99.5f, -92.7f,
            0.6f,
            clasificaciones.get(2),
            origenes.get(2),
            alcances.get(2), 
            estados.get(0),
            magnitudes.get(0), 
            new ArrayList<>(List.of(
                new CambioDeEstado(
                    LocalDateTime.of(2024, 5, 25, 14, 30, 1),
                    null,
                    estados.get(0),
                    empleados.get(2)
                )
            )),
            new ArrayList<>(List.of( seriesTemporales.get(2) ))
        ),
        //autodetectado, no se muestra
        new EventoSismico(
            LocalDateTime.of(2023, 8, 12, 0, 0, 1),
            -33.5f, -33.4f, 
            -69.5f, -69.4f,
            0.6f,
            clasificaciones.get(2),
            origenes.get(2),
            alcances.get(2), 
            estados.get(1),
            magnitudes.get(0), 
            new ArrayList<>(List.of(
                new CambioDeEstado(
                    LocalDateTime.of(2023, 8, 12, 0, 0, 1),
                    null,
                    estados.get(0),
                    empleados.get(2)
                )
            )),
            new ArrayList<>(List.of( seriesTemporales.get(3) ))
        )
    ));

    
}

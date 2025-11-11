package com.mycompany.ppai_cu_23.persistance;

import com.mycompany.ppai_cu_23.models.*;
import com.mycompany.ppai_cu_23.persistance.repository.EventoSismicoRepository;
import com.mycompany.ppai_cu_23.persistance.repository.SesionRepository;
import com.mycompany.ppai_cu_23.persistance.repository.SismografoRepository;
import com.mycompany.ppai_cu_23.persistance.repository.TipoDeDatoRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataBaseService {

    private static List<TipoDeDato> tiposDeDatoCache = new ArrayList<>();

    private static List<Sismografo> sismografosCache = new ArrayList<>();

    private static List<EventoSismico> eventosSismicosCache = new ArrayList<>();

    private static Sesion sesionActualCache = null;

    private static boolean instancia = false;

    // CONSULTAS A BASE DE DATOS

    public static void inicializarCache() {
        if (instancia) {
            return;
        }

        tiposDeDatoCache = cargarTiposDeDatoDesdeDB();
        sismografosCache = cargarSismografosDesdeDB();
        eventosSismicosCache = cargarEventosSismicosDesdeDB();
        sesionActualCache = cargarSesionActualDesdeDB();

        instancia = true;
    }

    private static void asegurarCacheInicializada() {
        if (!instancia) {
            throw new RuntimeException("¡Error! La caché de BaseDeDatos no ha sido inicializada. " +
                "Llame a DataBaseService.inicializarCache() al inicio de su programa.");
        }
    }

    public static TipoDeDato[] getTiposDeDato() {
        asegurarCacheInicializada();
        return tiposDeDatoCache.toArray(new TipoDeDato[0]);
    }

    public static Sismografo[] getSismografos() {
        asegurarCacheInicializada();
        return sismografosCache.toArray(new Sismografo[0]);
    }

    public static EventoSismico[] getEventosSismicos() {
        asegurarCacheInicializada();
        return eventosSismicosCache.toArray(new EventoSismico[0]);
    }

    public static Sesion getSesionActual() {
        asegurarCacheInicializada();
        return sesionActualCache;
    }

    // ACTUALIZACION

    public static void actualizarEventoSismico(EventoSismico eventoParaActualizar) {
        if (eventoParaActualizar == null) {
            return;
        }

        EntityManager em = null;
        try {

            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();

            EventoSismicoRepository repo = new EventoSismicoRepository(em);
            EventoSismico eventoActualizado = repo.update(eventoParaActualizar);

            em.getTransaction().commit();

            // Actualiza el cache si se actualizo un evento sismico
            if (eventosSismicosCache != null) {
                int index = -1;

                for (int i = 0; i < eventosSismicosCache.size(); i++) {
                    if (eventosSismicosCache.get(i).getId().equals(eventoActualizado.getId())) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    eventosSismicosCache.set(index, eventoActualizado);
                } else {
                    eventosSismicosCache.add(eventoActualizado);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {
            cerrarEntityMannager(em);
        }
    }


    // --- MÉTODOS PRIVADOS DE CARGA (SOLO USADOS POR inicializarCache()) ---

    private static List<TipoDeDato> cargarTiposDeDatoDesdeDB() {

        EntityManager em = null;
        List<TipoDeDato> resultados = Collections.emptyList();

        try {

            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();

            TipoDeDatoRepository repo = new TipoDeDatoRepository(em);

            resultados = repo.findAll();
            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {
            cerrarEntityMannager(em);
        }
        return new ArrayList<>(resultados); // Devolvemos una copia mutable
    }

    private static List<Sismografo> cargarSismografosDesdeDB() {

        EntityManager em = null;
        List<Sismografo> resultados = Collections.emptyList();

        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();

            SismografoRepository repo = new SismografoRepository(em);
            resultados = repo.findAll();

            em.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {
            cerrarEntityMannager(em);
        }
        return new ArrayList<>(resultados);
    }

    private static List<EventoSismico> cargarEventosSismicosDesdeDB() {

        EntityManager em = null;
        List<EventoSismico> resultados = Collections.emptyList();

        try {

            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();

            EventoSismicoRepository repo = new EventoSismicoRepository(em);

            resultados = repo.findAll();

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {
            cerrarEntityMannager(em);
        }
        return new ArrayList<>(resultados);
    }

    private static Sesion cargarSesionActualDesdeDB() {

        EntityManager em = null;
        Sesion sesionActiva = null;

        try {

            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();

            SesionRepository repo = new SesionRepository(em);

            sesionActiva = repo.findById(1L);

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {
            cerrarEntityMannager(em);
        }
        return sesionActiva;
    }


    // CARGAR NOMBRES

    public static String[] cargarNombresAlcanceSismo(){
        return Arrays.stream(nombresAlcanceSismo.values())
            .map(Enum::name)
            .toArray(String[]::new);
    }

    public static String[] cargarNombresOrigenDeGeneracion(){
        return Arrays.stream(nombresOrigenDeGeneracion.values())
            .map(Enum::name)
            .toArray(String[]::new);
    }

    // CONSTANTES

    public static enum nombresEstado {

        Auto_Detectado,
        Bloqueado_En_Revision,
        Pendiente_De_Revision,
        Rechazado

    }

    public static enum nombresAmbito {
        Evento_Sismico,
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

    private static void cerrarEntityMannager(EntityManager em){
        if (em != null) {
            em.close();
        }
    }

    private static void rollback(EntityManager em){
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}

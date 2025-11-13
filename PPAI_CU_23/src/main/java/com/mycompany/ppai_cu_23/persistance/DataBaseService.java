package com.mycompany.ppai_cu_23.persistance;

import com.mycompany.ppai_cu_23.models.*;
import com.mycompany.ppai_cu_23.persistance.repository.EventoSismicoRepository;
import com.mycompany.ppai_cu_23.persistance.repository.SesionRepository;
import com.mycompany.ppai_cu_23.persistance.repository.SismografoRepository;
import com.mycompany.ppai_cu_23.persistance.repository.TipoDeDatoRepository;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseService {

    // ACTUALIZACION

    public static void actualizarEventoSismico(EventoSismico eventoParaActualizar) {
        EntityManager em = null;
        try {

            em = JpaHelper.getEntityManager();
            em.getTransaction().begin();

            EventoSismicoRepository repo = new EventoSismicoRepository(em);
            EventoSismico eventoActualizado = repo.update(eventoParaActualizar);

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {
            cerrarEntityMannager(em);
        }
    }


    // CONSULTAS BASCIAS

    public static Sismografo[] getSismografos() {

        EntityManager em = null;
        List<Sismografo> resultados = Collections.emptyList();

        try {

            em = JpaHelper.getEntityManager();
            em.getTransaction().begin();

            SismografoRepository repo = new SismografoRepository(em);
            resultados = repo.findAllConEstacion();

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {

            cerrarEntityMannager(em);

        }
        return new ArrayList<>(resultados).toArray(new Sismografo[0]);
    }

    public static EventoSismico[] getEventosSismicos() {

        EntityManager em = null;
        List<EventoSismico> resultados = Collections.emptyList();

        try {

            em = JpaHelper.getEntityManager();
            em.getTransaction().begin();

            EventoSismicoRepository repo = new EventoSismicoRepository(em);

            resultados = repo.findAllParaGrilla();

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {

            cerrarEntityMannager(em);

        }
        return new ArrayList<>(resultados).toArray(new EventoSismico[0]);
    }

    public static Sesion getSesionActual() {

        EntityManager em = null;
        Sesion sesionActiva = null;

        try {

            em = JpaHelper.getEntityManager();
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

    // CONSULTA COMPLETA

    public static EventoSismico getEventoSeleccionado(Long id) {

        EntityManager em = null;
        EventoSismico resultado = null;

        try {

            em = JpaHelper.getEntityManager();
            em.getTransaction().begin();

            EventoSismicoRepository repo = new EventoSismicoRepository(em);

            resultado = repo.findByIdCompleto(id);

            inicializarEvento(resultado);

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();

            rollback(em);

        } finally {

            cerrarEntityMannager(em);

        }
        return resultado;
    }

    // HELPERS

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

    private static void inicializarEvento(EventoSismico evento) {

        if (evento.getCambioDeEstados() != null) {
            evento.getCambioDeEstados().size();
        }

        if (evento.getSerieTemporals() != null) {
            for (var serie : evento.getSerieTemporals()) {
                if (serie.getMuestraSismicas() != null) {
                    for (var muestra : serie.getMuestraSismicas()) {
                        if (muestra.getDetalleMuestraSismicas() != null) {
                            muestra.getDetalleMuestraSismicas().size();
                            for (var detalle : muestra.getDetalleMuestraSismicas()) {
                                if (detalle.getTipoDeDato() != null) {
                                    detalle.getTipoDeDato().getDenominacion();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

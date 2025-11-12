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

    // CONSULTAS

    public static Sismografo[] getSismografos() {
        return cargarSismografosDesdeDB().toArray(new Sismografo[0]);
    }

    public static EventoSismico[] getEventosSismicos() {
        return cargarEventosSismicosDesdeDB().toArray(new EventoSismico[0]);
    }

    public static Sesion getSesionActual() {
        return cargarSesionActualDesdeDB();
    }

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


    // METODOS DE CARGA

    private static List<Sismografo> cargarSismografosDesdeDB() {

        EntityManager em = null;
        List<Sismografo> resultados = Collections.emptyList();

        try {

            em = JpaHelper.getEntityManager();
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

            em = JpaHelper.getEntityManager();
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

}

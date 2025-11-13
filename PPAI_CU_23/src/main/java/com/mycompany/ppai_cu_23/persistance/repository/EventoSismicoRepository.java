package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.models.EventoSismico;
import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EventoSismicoRepository extends BaseRepository<EventoSismico, Long> {

    public EventoSismicoRepository(EntityManager em) {
        super(em, EventoSismico.class);
    }

    public List<EventoSismico> findAllParaGrilla() {
        String jpql = """
            SELECT DISTINCT e 
            FROM EventoSismico e
            LEFT JOIN FETCH e.estadoActual
            ORDER BY e.fechaHoraOcurrencia DESC
        """;

        TypedQuery<EventoSismico> query = em.createQuery(jpql, EventoSismico.class);
        return query.getResultList();
    }

    public EventoSismico findByIdCompleto(Long eventoId) {

        // EVENTO

        String jpql1 = """
            SELECT DISTINCT e 
            FROM EventoSismico e
            LEFT JOIN FETCH e.estadoActual
            LEFT JOIN FETCH e.clasificacionSismo
            LEFT JOIN FETCH e.origenDeGeneracion
            LEFT JOIN FETCH e.alcanceSismo
            LEFT JOIN FETCH e.magnitud
            WHERE e.id = :eventoId
        """;

        TypedQuery<EventoSismico> query1 = em.createQuery(jpql1, EventoSismico.class);
        query1.setParameter("eventoId", eventoId);
        EventoSismico evento = query1.getSingleResult();

        // ESTADOS

        String jpql2 = """
            SELECT DISTINCT e 
            FROM EventoSismico e
            LEFT JOIN FETCH e.cambioDeEstados ce
            LEFT JOIN FETCH ce.estado
            LEFT JOIN FETCH ce.empleado
            WHERE e.id = :eventoId
        """;

        TypedQuery<EventoSismico> query2 = em.createQuery(jpql2, EventoSismico.class);
        query2
            .setParameter("eventoId", eventoId)
            .getSingleResult();


        // SERIES TEMPORALES

        String jpql3 = """
            SELECT DISTINCT st
            FROM SerieTemporal st
            LEFT JOIN FETCH st.estado
            LEFT JOIN FETCH st.muestraSismicas ms
            WHERE st IN (
                SELECT s FROM EventoSismico e 
                JOIN e.serieTemporals s 
                WHERE e.id = :eventoId
            )
        """;

        em.createQuery(jpql3)
            .setParameter("eventoId", eventoId)
            .getResultList();

        // MUESTRAS SISMICAS y SERIES TEMPORALES

        String jpql4 = """
            SELECT DISTINCT ms
            FROM MuestraSismica ms
            LEFT JOIN FETCH ms.detalleMuestraSismicas dms
            LEFT JOIN FETCH dms.tipoDeDato
            WHERE ms IN (
                SELECT m FROM SerieTemporal st 
                JOIN st.muestraSismicas m 
                WHERE st IN (
                    SELECT s FROM EventoSismico e 
                    JOIN e.serieTemporals s 
                    WHERE e.id = :eventoId
                )
            )
        """;

        em.createQuery(jpql4)
            .setParameter("eventoId", eventoId)
            .getResultList();

        return evento;
    }

}

package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.models.Sismografo;
import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class SismografoRepository extends BaseRepository<Sismografo, Long> {

    public SismografoRepository(EntityManager em) {
        super(em, Sismografo.class);
    }

    public List<Sismografo> findAllConEstacion() {
        String jpql = """
            SELECT DISTINCT s 
            FROM Sismografo s
            LEFT JOIN FETCH s.estacionSismologica
            ORDER BY s.identificadorSismografo
        """;

        TypedQuery<Sismografo> query = em.createQuery(jpql, Sismografo.class);
        return query.getResultList();
    }

    public Sismografo findByIdCompleto(Long sismografoId) {

        // SISMOGRAFO MAS ESTACION
        String jpql1 = """
            SELECT DISTINCT s 
            FROM Sismografo s
            LEFT JOIN FETCH s.estacionSismologica
            WHERE s.id = :sismografoId
        """;

        TypedQuery<Sismografo> query1 = em.createQuery(jpql1, Sismografo.class);
        query1.setParameter("sismografoId", sismografoId);
        Sismografo sismografo = query1.getSingleResult();

        // SERIES TEMPORALES
        String jpql2 = """
            SELECT DISTINCT s 
            FROM Sismografo s
            LEFT JOIN FETCH s.serieTemporals
            WHERE s.id = :sismografoId
        """;

        em.createQuery(jpql2, Sismografo.class)
            .setParameter("sismografoId", sismografoId)
            .getSingleResult();

        // CAMBIOS DE ESTADO
        String jpql3 = """
            SELECT DISTINCT s 
            FROM Sismografo s
            LEFT JOIN FETCH s.cambioDeEstados
            WHERE s.id = :sismografoId
        """;

        em.createQuery(jpql3, Sismografo.class)
            .setParameter("sismografoId", sismografoId)
            .getSingleResult();

        return sismografo;
    }

}

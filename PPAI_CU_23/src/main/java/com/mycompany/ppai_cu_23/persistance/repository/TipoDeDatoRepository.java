package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.models.TipoDeDato;
import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import jakarta.persistence.EntityManager;

public class TipoDeDatoRepository extends BaseRepository<TipoDeDato, Long> {

    public TipoDeDatoRepository(EntityManager em) {
        super(em, TipoDeDato.class);
    }

}

package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import com.mycompany.ppai_cu_23.refactor.Estado;
import jakarta.persistence.EntityManager;

public class EstadoRepository extends BaseRepository<Estado, Long> {

    public EstadoRepository(EntityManager em) {
        super(em, Estado.class);
    }

}

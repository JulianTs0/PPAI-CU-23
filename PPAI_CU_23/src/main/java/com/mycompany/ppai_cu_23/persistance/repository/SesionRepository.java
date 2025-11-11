package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.models.Sesion;
import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import jakarta.persistence.EntityManager;

public class SesionRepository extends BaseRepository<Sesion, Long> {

    public SesionRepository(EntityManager em) {
        super(em, Sesion.class);
    }

}

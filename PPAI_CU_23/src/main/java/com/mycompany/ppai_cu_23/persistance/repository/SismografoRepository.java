package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.models.Sismografo;
import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import jakarta.persistence.EntityManager;

public class SismografoRepository extends BaseRepository<Sismografo, Long> {

    public SismografoRepository(EntityManager em) {
        super(em, Sismografo.class);
    }

}

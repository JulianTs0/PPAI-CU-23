package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import jakarta.persistence.EntityManager;

public class CambioDeEstadoRepository extends BaseRepository<CambioDeEstado, Long> {

    public CambioDeEstadoRepository(EntityManager em) {
        super(em, CambioDeEstado.class);
    }

}

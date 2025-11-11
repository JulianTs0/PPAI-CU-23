package com.mycompany.ppai_cu_23.persistance.repository;

import com.mycompany.ppai_cu_23.models.CambioDeEstado;
import com.mycompany.ppai_cu_23.models.EventoSismico;
import com.mycompany.ppai_cu_23.persistance.BaseRepository;
import jakarta.persistence.EntityManager;

public class EventoSismicoRepository extends BaseRepository<EventoSismico, Long> {

    public EventoSismicoRepository(EntityManager em) {
        super(em, EventoSismico.class);
    }

}

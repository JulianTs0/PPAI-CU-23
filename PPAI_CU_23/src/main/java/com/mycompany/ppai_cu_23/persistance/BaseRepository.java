package com.mycompany.ppai_cu_23.persistance;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepository<T, ID extends Serializable>{

    protected final EntityManager em;

    protected final Class<T> entityClass;

    public BaseRepository(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    public T findById(ID id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

}

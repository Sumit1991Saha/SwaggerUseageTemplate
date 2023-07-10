package com.saha.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.entity.AbstractEntityPersister;

import java.util.List;
import javax.persistence.EntityManager;

public class CrudService<T> {

    Class<?> entityClass;
    private EntityManager em;

    public CrudService(Class<?> clazz, EntityManager entityManager) {
        this.em = entityManager;
        this.entityClass = clazz;
    }
    
    public void create(T object) {
        em.persist(object);
    }

    public void update(T object) {
        em.persist(object);
    }

    public long getNextSequenceId(Class<?> entityClass, EntityManager entityManager) {
        Session hibernateSession = getHibernateSession(entityManager);
        SessionFactory sessionFactory = hibernateSession.getSessionFactory();
        return (long) (Long) ((AbstractEntityPersister) sessionFactory
                .getClassMetadata(entityClass)).getIdentifierGenerator()
                .generate((SessionImplementor) hibernateSession, null);
    }

    private Session getHibernateSession(EntityManager entityManager) {
        Object delegate = entityManager.getDelegate();
        while (delegate instanceof EntityManager) {
            delegate = ((EntityManager) delegate).getDelegate();
        }
        return (Session) delegate;
    }

    public void removeById(Long id) {
        T object = (T) em.find(entityClass, id);
        if (object != null) {
            em.remove(object);
        }
    }

    public T findById( Long id) {
        return (T) em.find(entityClass, id);
    }

    public List<T> findAll() {
        String queryString = "select e from " + entityClass.getSimpleName() + " e";
        List<T> results = (List<T>) em
                .createQuery(queryString, entityClass)
                .getResultList();
        return results;
    }
}

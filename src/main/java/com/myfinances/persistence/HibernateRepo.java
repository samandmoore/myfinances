package com.myfinances.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Sam Moore
 * @since 8/5/13 8:57 AM
 */
public class HibernateRepo<E, K extends Serializable> implements GenericRepo<E, K> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<? extends E> daoType;

    @SuppressWarnings("unchecked")
    public HibernateRepo() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @Override
    public List<E> list() {
        return currentSession().createCriteria(daoType).list();
    }
}
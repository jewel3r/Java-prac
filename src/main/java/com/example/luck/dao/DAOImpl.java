package com.example.luck.dao;

import com.example.luck.Client;
import com.example.luck.CommonEntity;
import com.example.luck.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class DAOImpl<T extends CommonEntity<ID>, ID extends Serializable> implements DAO<T> {

    protected Class<T> persistentClass;
    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public DAOImpl(Class<T> entityClass){

        this.persistentClass = entityClass;
    }

    @Override
    public T get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(persistentClass, id);
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    @Override
    public void save(T t) {
        try (Session session = sessionFactory.openSession()) {
            if (t.getId() != null) {
                t.setId(null);
            }
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(T t) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(t);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T t) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (t != null) {
                session.remove(t);
            }
            session.getTransaction().commit();
        }
    }
}

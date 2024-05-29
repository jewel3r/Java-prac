package com.example.luck.dao;

import com.example.luck.Services;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class ServiceDAOImpl extends DAOImpl<Services, Long> implements ServiceDAO {

    public ServiceDAOImpl() {
        super(Services.class);
    }

    @Override
    public List<Services> getAllSortedById() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Services> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Services> t =  session.createQuery(criteriaQuery).getResultList();
            t.sort(Comparator.comparing(Services::getId));
            return t;
        }
    }
}

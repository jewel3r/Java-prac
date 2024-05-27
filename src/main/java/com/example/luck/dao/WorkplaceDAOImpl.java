package com.example.luck.dao;

import com.example.luck.Workplace;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class WorkplaceDAOImpl extends DAOImpl<Workplace, Long> implements WorkplaceDAO {

    public WorkplaceDAOImpl() {
        super(Workplace.class);
    }

    @Override
    public List<Workplace> getAllSortedById() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Workplace> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Workplace> t =  session.createQuery(criteriaQuery).getResultList();
            t.sort(Comparator.comparing(Workplace::getId));
            return t;
        }
    }
}

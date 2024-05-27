package com.example.luck.dao;

import com.example.luck.Employee;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl extends DAOImpl<Employee, Long> implements EmployeeDAO {

    public EmployeeDAOImpl() {
        super(Employee.class);
    }

    @Override
    public List<Employee> getAllSortedById() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Employee> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Employee> t =  session.createQuery(criteriaQuery).getResultList();
            t.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
            return t;
        }
    }
}

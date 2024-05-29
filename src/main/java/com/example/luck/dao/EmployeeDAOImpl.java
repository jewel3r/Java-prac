package com.example.luck.dao;

import com.example.luck.Client;
import com.example.luck.Employee;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
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
            t.sort(Comparator.comparing(Employee::getId));
            return t;
        }
    }

    @Override
    public List<Employee> getAllSortedByName() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Employee> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Employee> t =  session.createQuery(criteriaQuery).getResultList();
            t.sort(Comparator.comparing(Employee::getName));
            return t;
        }
    }

    @Override
    public List<Employee> getAllWithFilter(String keyword) {
        List<Employee> result = this.getAllSortedByName();
        if (keyword != null) {
            List<Employee> temp = new ArrayList<>();
            for (Employee emp : result) {
                if (emp.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    temp.add(emp);
                }
            }
            result = temp;
        }
        return result;
    }
}

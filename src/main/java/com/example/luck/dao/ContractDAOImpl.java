package com.example.luck.dao;

import com.example.luck.Client;
import com.example.luck.Contract;
import com.example.luck.Employee;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Repository
public class ContractDAOImpl extends DAOImpl<Contract, Long> implements ContractDAO {

    public ContractDAOImpl()
    {
        super(Contract.class);
    }

    @Override
    public List<Contract> getAllSortedByDate() {

        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Contract> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Contract> t =  session.createQuery(criteriaQuery).getResultList();

            t.sort((o1, o2) -> Comparator.nullsLast(Date::compareTo).compare(o2.getRealDate(), o1.getRealDate()));
            return t;
        }
    }

    @Override
    public List<Contract> getAllSortedById() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Contract> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Contract> t = session.createQuery(criteriaQuery).getResultList();
            t.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
            return t;
        }
    }

    @Override
    public Employee getEmployeeById(Long id)
    {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("from Contract where id = :id", Contract.class);
            query.setParameter("id", id);
            return query.uniqueResult().getEmployee();
        }
    }

    @Override
    public Client getClientById(Long id)
    {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("from Contract where id = :id", Contract.class);
            query.setParameter("id", id);
            return query.uniqueResult().getClient();
        }
    }

    @Override
    public List<Contract> getContractByDataRange(Date start, Date end)
    {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("from Contract where signDate between :start and :end", Contract.class);
            query.setParameter("start", start);
            query.setParameter("end", end);
            return query.getResultList();
        }
    }

    @Override
    public List<Contract> getContractByClientId(Long clientId)
    {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("from Contract where client.id = :clientId", Contract.class);
            query.setParameter("clientId", clientId);
            return query.getResultList();
        }
    }

    @Override
    public List<Contract> getContractByEmployeeId(Long employeeId)
    {
        try (Session session = sessionFactory.openSession()) {
            Query<Contract> query = session.createQuery("from Contract where employee.id = :employeeId", Contract.class);
            query.setParameter("employeeId", employeeId);
            return query.getResultList();
        }
    }
}

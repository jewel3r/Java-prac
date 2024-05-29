package com.example.luck.dao;

import com.example.luck.Client;
import com.example.luck.Employee;
import com.example.luck.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class ClientDAOImpl extends DAOImpl<Client, Long> implements ClientDAO{

    public ClientDAOImpl() {
        super(Client.class);
    }
    @Override
    public List<Client> getAllSortedById() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Client> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Client> t =  session.createQuery(criteriaQuery).getResultList();
            t.sort(Comparator.comparing(Client::getId));
            return t;
        }
    }

    @Override
    public List<Client> getAllSortedByName() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Client> criteriaQuery = session.getCriteriaBuilder().createQuery(persistentClass);
            criteriaQuery.from(persistentClass);
            List<Client> t =  session.createQuery(criteriaQuery).getResultList();
            t.sort(Comparator.comparing(Client::getClientName));
            return t;
        }
    }

    @Override
    public List<Client> getAllWithFilter(String keyword) {
        List<Client> result = this.getAllSortedByName();
        if (keyword != null) {
            List<Client> temp = new ArrayList<>();
            for (Client client : result) {
                if (client.getClientName().toLowerCase().contains(keyword.toLowerCase())) {
                    temp.add(client);
                }
            }
            result = temp;
        }
        return result;
    }
}

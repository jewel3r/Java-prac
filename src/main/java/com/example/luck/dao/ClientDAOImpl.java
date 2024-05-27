package com.example.luck.dao;

import com.example.luck.Client;
import com.example.luck.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientDAOImpl extends DAOImpl<Client, Long> implements ClientDAO{

    public ClientDAOImpl() {
        super(Client.class);
    }
}

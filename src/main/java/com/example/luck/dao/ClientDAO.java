package com.example.luck.dao;

import com.example.luck.Client;

import java.util.List;

public interface ClientDAO extends DAO<Client>{
    List<Client> getAllSortedById();
    List<Client> getAllSortedByName();
    List<Client> getAllWithFilter(String Keyword);
}

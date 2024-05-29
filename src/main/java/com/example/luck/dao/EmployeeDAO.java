package com.example.luck.dao;

import com.example.luck.Client;
import com.example.luck.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface EmployeeDAO extends DAO<Employee>{

    List<Employee> getAllSortedById();
    List<Employee> getAllSortedByName();
    List<Employee> getAllWithFilter(String Keyword);
}
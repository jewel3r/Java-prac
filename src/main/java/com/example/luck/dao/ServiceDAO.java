package com.example.luck.dao;

import com.example.luck.Services;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDAO extends DAO<Services>{

    List<Services> getAllSortedById();
}

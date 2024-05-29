package com.example.luck.dao;

import com.example.luck.Workplace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkplaceDAO extends DAO<Workplace>{

    List<Workplace> getAllSortedById();
}

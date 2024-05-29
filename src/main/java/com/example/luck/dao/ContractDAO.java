package com.example.luck.dao;

import com.example.luck.Client;
import com.example.luck.Contract;
import com.example.luck.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Repository
public interface ContractDAO extends DAO<Contract>{

    List<Contract> getAllSortedByDate();
    List<Contract> getAllSortedById();

    Employee getEmployeeById(Long id);
    Client getClientById(Long id);

    List<Contract> getContractByDataRange(Date start, Date end);

    List<Contract> getContractByClientId(Long clientId);
    List<Contract> getContractByEmployeeId(Long employeeId);
}

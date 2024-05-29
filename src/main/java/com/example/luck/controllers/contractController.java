package com.example.luck.controllers;
import com.example.luck.*;
import com.example.luck.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class contractController {

    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private ServiceDAO serviceDAO;
    static int PAGE_SZ = 4;

    @GetMapping("/contracts")
    public String getContractsPage(Model model, @RequestParam(defaultValue = "0") int page, Long keyid) {

        List<Contract> contracts = new ArrayList<>();
        if (keyid == null) {
            contracts = contractDAO.getAllSortedById();
        } else {
            Contract temp = contractDAO.get(keyid);
            if (temp != null) {
                contracts.add(temp);
            }
        }
        model.addAttribute("list_sz", contracts.size());
        if (PAGE_SZ * page > contracts.size()) {
            return "error";
        }
        model.addAttribute("contracts", contracts.subList(PAGE_SZ * page, Math.min(PAGE_SZ * page + PAGE_SZ, contracts.size())));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", PAGE_SZ);
        return "contracts";
    }

    @GetMapping(value = "/singleContract")
    public String getSingleContractPage(Model model, @RequestParam(name = "id") Long id) {
        Contract contract = contractDAO.get(id);

        if (contract == null) {
            return "error";
        }

        model.addAttribute("contract", contract);
        return "singleContract";
    }

    @GetMapping(value = "/editContract")
    public String getEditContractPage(Model model, @RequestParam(name = "contractId", required = false) Long contractId) {

        Contract contract;
        if (contractId != null) {
            contract = contractDAO.get(contractId);

            if (contract == null) {
                return "error";
            }
        } else {
            contract = new Contract();
        }
        model.addAttribute("contract", contract);
        model.addAttribute("contractDAO", contractDAO);
        model.addAttribute("clientDAO", clientDAO);
        model.addAttribute("employeeDAO", employeeDAO);
        model.addAttribute("serviceDAO", serviceDAO);
        return "editContract";
    }

    @PostMapping(value = "/removeContract")
    public String removeContractPage(@RequestParam(name = "contractId") Long contractId) {
        contractDAO.delete(contractDAO.get(contractId));
        return "redirect:/contracts";
    }

    @PostMapping("/saveContract")
    public String saveContractPage(@RequestParam(name = "contractId", required = false) Long contractId,
                                 @RequestParam(name = "clientId") Long clientId,
                                 @RequestParam(name = "employeeId") Long employeeId,
                                 @RequestParam(name = "serviceId") Long serviceId,
                                 @RequestParam(name = "signDate") String signDate,
                                 Model model) {
        Contract contract = contractId == null ? null : contractDAO.get(contractId);

        if (contract != null) {
            contract.setClient(clientDAO.get(clientId));
            contract.setEmployee(employeeDAO.get(employeeId));
            contract.setService(serviceDAO.get(serviceId));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(signDate, formatter);
            contract.setSignDate(java.sql.Date.valueOf(date));
            contractDAO.update(contract);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(signDate, formatter);
            contract = new Contract(contractId, clientDAO.get(clientId), employeeDAO.get(employeeId), serviceDAO.get(serviceId), java.sql.Date.valueOf(date));
            contractDAO.save(contract);
        }

        return "redirect:/contracts";
    }
}

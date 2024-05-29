package com.example.luck.controllers;

import com.example.luck.Client;
import com.example.luck.Employee;
import com.example.luck.dao.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class clientController {
    @Autowired
    private ClientDAO clientDAO;
    static int PAGE_SZ = 4;

    @GetMapping(value = "/clients")
    public String getClientsPage(Model model, @RequestParam(defaultValue = "0") int page, String keyword) {
        List<Client> clients = clientDAO.getAllWithFilter(keyword);

        model.addAttribute("list_sz", clients.size());
        if (PAGE_SZ * page > clients.size()) {
            return "error";
        }
        model.addAttribute("sub_clients", clients.subList(PAGE_SZ * page, Math.min(PAGE_SZ * page + PAGE_SZ, clients.size())));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", PAGE_SZ);
        model.addAttribute("keyword", keyword);
        return "clients";
    }

    @GetMapping(value = "/singleClient")
    public String getSingleClientPage(Model model, @RequestParam(required = true) Long id) {
        Client client = clientDAO.get(id);

        if (client == null) {
            return "error";
        }

        model.addAttribute("client", client);
        return "singleClient";
    }

    @GetMapping(value = "/editClient")
    public String getEditClientPage(Model model, @RequestParam(name = "clientId", required = false) Long clientId) {

        Client client;
        if (clientId != null) {
            client = clientDAO.get(clientId);

            if (client == null) {
                return "error";
            }
        } else {
            client = new Client();
        }
        model.addAttribute("client", client);
        model.addAttribute("clientDAO", clientDAO);
        return "editClient";
    }

    @PostMapping(value = "/removeClient")
    public String removeClientPage(@RequestParam(name = "clientId") Long clientId) {
        clientDAO.delete(clientDAO.get(clientId));
        return "redirect:/clients";
    }

    @PostMapping(value = "/saveClient")
    public String saveClientPage(@RequestParam(name = "clientId", required = false) Long clientId,
                                 @RequestParam(name = "clientName", required = false) String clientName,
                                 @RequestParam(name = "clientAddress", required = false) String clientAddress,
                                 @RequestParam(name = "clientPhone", required = false) String clientPhone,
                                 @RequestParam(name = "clientEmail", required = false) String clientEmail,
                                 @RequestParam(name = "clientBirthDate", required = false) String clientBirthDate,
                                 Model model) {
        Client client = clientId == null ? null : clientDAO.get(clientId);

        if (client != null) {
            client.setClientName(clientName);
            client.setAddress(clientAddress);
            client.setPhone(clientPhone);
            client.setEmail(clientEmail);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(clientBirthDate, formatter);
            client.setBirthDate(java.sql.Date.valueOf(date));
            clientDAO.update(client);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(clientBirthDate, formatter);
            client = new Client(clientId, clientName, "", clientAddress, clientPhone, clientEmail, java.sql.Date.valueOf(date));
            clientDAO.save(client);
        }

        return "redirect:/clients";
    }

}

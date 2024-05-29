package com.example.luck.controllers;

import com.example.luck.Client;
import com.example.luck.Services;
import com.example.luck.dao.ServiceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Controller
public class serviceController {
    @Autowired
    private ServiceDAO serviceDAO;
    static int PAGE_SZ = 4;

    @GetMapping(value = "/services")
    public String getServicesPage(Model model, @RequestParam(defaultValue = "0") int page) {
        List<Services> services = serviceDAO.getAllSortedById();
        model.addAttribute("list_sz", services.size());
        if (PAGE_SZ * page > services.size()) {
            return "error";
        }
        model.addAttribute("services", services.subList(PAGE_SZ * page, Math.min(PAGE_SZ * page + PAGE_SZ, services.size())));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", PAGE_SZ);
        return "services";
    }
}

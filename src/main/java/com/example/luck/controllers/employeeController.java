package com.example.luck.controllers;

import com.example.luck.Client;
import com.example.luck.Employee;
import com.example.luck.Workplace;
import com.example.luck.dao.EmployeeDAO;
import com.example.luck.dao.WorkplaceDAO;
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
public class employeeController {
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private WorkplaceDAO workplaceDAO;
    static int PAGE_SZ = 4;

    @GetMapping("/employees")
    public String getEmployeesPage(Model model, @RequestParam(defaultValue = "0") int page, String keyword) {
        List<Employee> employees = employeeDAO.getAllWithFilter(keyword);

        model.addAttribute("list_sz", employees.size());
        if (PAGE_SZ * page > employees.size()) {
            return "error";
        }
        model.addAttribute("employees", employees.subList(PAGE_SZ * page, Math.min(PAGE_SZ * page + PAGE_SZ, employees.size())));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", PAGE_SZ);
        model.addAttribute("keyword", keyword);

        return "employees";
    }

    @GetMapping(value = "/singleEmployee")
    public String getSingleEmployeePage(Model model, @RequestParam(name = "id") Long id) {
        Employee employee = employeeDAO.get(id);

        if (employee == null) {
            return "error";
        }

        model.addAttribute("employee", employee);
        return "singleEmployee";
    }

    @GetMapping(value = "/editEmployee")
    public String getEditEmployeePage(Model model, @RequestParam(name = "employeeId", required = false) Long employeeId) {

        Employee employee;
        if (employeeId != null) {
            employee = employeeDAO.get(employeeId);

            if (employee == null) {
                return "error";
            }
        } else {
            employee = new Employee();
        }
        model.addAttribute("employee", employee);
        model.addAttribute("employeeDAO", employeeDAO);
        List<Workplace> workplaces = workplaceDAO.getAll();
        model.addAttribute("workplaces", workplaces);
        return "editEmployee";
    }

    @PostMapping(value = "/removeEmployee")
    public String removeEmployeePage(@RequestParam(name = "employeeId") Long employeeId) {
        employeeDAO.delete(employeeDAO.get(employeeId));
        return "redirect:/employees";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployeePage(@RequestParam(name = "employeeId", required = false) Long employeeId,
                                 @RequestParam(name = "employeeName", required = false) String employeeName,
                                 @RequestParam(name = "employeeAddress", required = false) String employeeAddress,
                                 @RequestParam(name = "employeePhone", required = false) String employeePhone,
                                 @RequestParam(name = "employeeEmail", required = false) String employeeEmail,
                                 @RequestParam(name = "employeeEducation", required = false) String employeeEducation,
                                 @RequestParam(name = "employeeWorkplaceId", required = false) Long employeeWorkplaceId,
                                 @RequestParam(name = "employeeBirthDate", required = false) String employeeBirthDate,
                                 Model model) {
        Employee employee = employeeId == null ? null : employeeDAO.get(employeeId);

        if (employee != null) {
            employee.setName(employeeName);
            employee.setAddress(employeeAddress);
            employee.setPhone(employeePhone);
            employee.setEmail(employeeEmail);
            employee.setEducation(employeeEducation);
            employee.setWorkplace(workplaceDAO.get(employeeWorkplaceId));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(employeeBirthDate, formatter);
            employee.setBirthDate(java.sql.Date.valueOf(date));
            employeeDAO.update(employee);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(employeeBirthDate, formatter);
            employee = new Employee(employeeId, employeeName, employeeAddress, employeePhone, employeeEmail, employeeEducation, workplaceDAO.get(employeeWorkplaceId), java.sql.Date.valueOf(date));
            employeeDAO.save(employee);
        }

        return "redirect:/employees";
    }
}

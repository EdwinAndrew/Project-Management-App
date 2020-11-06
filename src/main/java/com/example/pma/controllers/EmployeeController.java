package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.entities.Employee;
import com.example.pma.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empRepo; // field injection


    @GetMapping
    public String displayEmployees(Model model) {
        List<Employee> employees = empRepo.getAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        Employee aEmployee = new Employee();
        model.addAttribute("employee", aEmployee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, Employee employee) {
        empRepo.save(employee);
        return "redirect:/employees/new";
    }
}

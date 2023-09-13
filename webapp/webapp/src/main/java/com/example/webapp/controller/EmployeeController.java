package com.example.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.webapp.CustomProperties;
import com.example.webapp.model.Employee;
import com.example.webapp.repository.EmployeeProxy;
import com.example.webapp.service.EmployeeService;

import org.springframework.ui.Model;

@Controller
public class EmployeeController {

    EmployeeService service = new EmployeeService();
    @Autowired
    private CustomProperties properties = new CustomProperties();
    
    
    @GetMapping("/")
    public String home(Model model) {
        System.out.println(properties);
        Iterable<Employee> listEmployee = service.getEmployees();
        model.addAttribute("employees", listEmployee);
        System.out.println(properties.getApiUrl());
        return "home";
    }
/*
    @GetMapping("/employees")
    public String employees() {
        return "employees";
    }
 */
}

package com.example.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;


import com.example.webapp.CustomProperties;
import com.example.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeProxy {

    @Autowired
    private CustomProperties props ;

    public Iterable<Employee> getEmployees(){

        System.out.println(props);
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
                );

        log.debug("Get Employees call " + response.getStatusCode().toString());
        
        return response.getBody();
    }

    public Employee createEmployee(Employee e) {
        String baseApiUrl = props.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";
    
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
            createEmployeeUrl,
            HttpMethod.POST,
            request,
            Employee.class);
    
        log.debug("Create Employee call " + response.getStatusCode().toString());
    
        return response.getBody();
    }

    public Employee getEmployee(int id) {
        String baseApiUrl = this.props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;
    
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
            getEmployeeUrl,
            HttpMethod.GET,
            null,
            Employee.class);
    
        log.debug("Get Employee call " + response.getStatusCode().toString());
    
        return response.getBody();
    }

    public Employee deleteEmployee (int id) {
        String baseApiUrl = this.props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;
    
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
            deleteEmployeeUrl,
            HttpMethod.DELETE,
            null,
            Employee.class);
    
        log.debug("Delete Employee call " + response.getStatusCode().toString());
    
        return response.getBody();
    }

    public Employee updateEmployee(Employee e) {
        String baseApiUrl = this.props.getApiUrl();
        String updateEmployeeUrl = baseApiUrl + "/employee/" + e.getId();
    
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
            updateEmployeeUrl,
            HttpMethod.PUT,
            request,
            Employee.class);
    
        log.debug("Update Employee call " + response.getStatusCode().toString());
    
        return response.getBody();
    }


}
package com.example.employee.service.impl;

import com.example.employee.model.Employee;
import com.example.employee.repository.IEmployeeRepository;
import com.example.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private HttpServletRequest req;

    @Autowired
    private IEmployeeRepository employeeRepository;


    @Override
    public Employee getEmployeeByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Override
    public Employee findByEmailAndPassword(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public List<Employee> allProgress() {
        return employeeRepository.findAll();
    }
}

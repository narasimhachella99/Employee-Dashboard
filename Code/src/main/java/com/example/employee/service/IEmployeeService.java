package com.example.employee.service;

import com.example.employee.model.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee getEmployeeByEmailAndPassword(String email,String password);

   Employee getEmployeeByEmail(String email);

   Employee saveEmployee(Employee e);

    List<Employee> findAll();

    Employee findByName(String name);

    Employee findByEmailAndPassword(String email,String password);

    List<Employee> allProgress();
}

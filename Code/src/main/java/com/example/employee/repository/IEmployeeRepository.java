package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findByEmailAndPassword(String email,String password);

    Employee findByEmail(String email);

    Employee findByName(String name);
}

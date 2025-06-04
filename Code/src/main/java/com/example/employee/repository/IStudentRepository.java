package com.example.employee.repository;

import com.example.employee.model.Student;
import com.example.employee.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends JpaRepository<Student,Long> {

    Student findByEmailAndPassword(String email,String password);

    Student findByEmail(String email);

}

package com.example.employee.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private String name;
    private String email;
    private String password;
    private String rollNo;
    private String dept;
    private String employeeName;


    public Student(String name, String email,String password,String rollNo, String dept) {
        this.name = name;
        this.email=email;
        this.password=password;
        this.rollNo = rollNo;
        this.dept = dept;

    }
    public Student(String employeeName){
        this.employeeName=employeeName;

    }
}

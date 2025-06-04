package com.example.employee.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String dept;
    private Long date;


    public Employee(String name, String email, String password, String dept) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dept = dept;

    }
    public Employee(String date){
        this.date= Long.valueOf(date);
    }
}

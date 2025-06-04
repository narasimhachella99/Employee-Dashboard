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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String studentName;
    private String employeeName;
    public Feedback(String studentName, String description,String employeeName) {
        this.studentName = studentName;
        this.description = description;
        this.employeeName=employeeName;
    }

    public Feedback(String description) {
        this.description=description;
    }
}

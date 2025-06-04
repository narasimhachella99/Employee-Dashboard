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
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String assignedTo;

    private Integer percentage;

    private Boolean isAssigned=false;
    private String date;

    public Subject(String name) {
        this.name = name;
        isAssigned = false;
        percentage = 0;
        this.date= LocalDate.now().toString();

    }

}

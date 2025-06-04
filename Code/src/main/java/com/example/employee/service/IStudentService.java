package com.example.employee.service;


import com.example.employee.model.Feedback;
import com.example.employee.model.Student;
import com.example.employee.model.Subject;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface IStudentService {


    Student getStudentByEmailAndPassword(String email,String password);

    Student getStudentByEmail(String email);
    Student saveStudent(Student student);

    Subject saveSubject(Subject s);

    List<Subject> allSub();


    Subject getById(Long id);

    Feedback saveFeedback(Feedback f);

    List<Feedback> allFeedbacks();
    List<Student> allStudents();
    void updatePassword(Student s);

    Student studentGetById(Long id);
}

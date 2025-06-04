package com.example.employee.service.impl;

import com.example.employee.model.Feedback;
import com.example.employee.model.Student;
import com.example.employee.model.Subject;
import com.example.employee.repository.IFeedbackRepository;
import com.example.employee.repository.IStudentRepository;
import com.example.employee.repository.ISubjectRepository;
import com.example.employee.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private ISubjectRepository subjectRepository;
    @Autowired
    private IFeedbackRepository feedbackRepository;


    @Override
    public Student getStudentByEmailAndPassword(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Subject saveSubject(Subject s) {
        return subjectRepository.save(s);
    }

    @Override
    public List<Subject> allSub() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getById(Long id) {
        return subjectRepository.findById(id).get();
    }

    @Override
    public Feedback saveFeedback(Feedback f) {
        return feedbackRepository.save(f);
    }

    @Override
    public List<Feedback> allFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Student> allStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void updatePassword(Student s) {
        studentRepository.save(s);
    }

    @Override
    public Student studentGetById(Long id) {
        return studentRepository.findById(id).get();
    }

}

package com.example.employee.service.impl;

import com.example.employee.model.Student;
import com.example.employee.model.Subject;
import com.example.employee.repository.ISubjectRepository;
import com.example.employee.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements ISubjectService {


    @Autowired
    private ISubjectRepository subjectRepository;


    @Override
    public List<Subject> allProgress() {
        return subjectRepository.findAll();
    }


}

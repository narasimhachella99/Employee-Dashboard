package com.example.employee.repository;

import com.example.employee.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectRepository extends JpaRepository <Subject,Long>{

    Subject findByName(String name);
}

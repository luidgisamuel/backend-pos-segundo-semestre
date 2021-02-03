package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Grade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
  Grade findGradeById(Long id); 
  List<Grade> findBySubject_Id(Long id);
}

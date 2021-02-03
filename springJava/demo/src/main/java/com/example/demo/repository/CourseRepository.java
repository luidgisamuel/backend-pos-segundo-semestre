package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Course findCourseById(Long id); 
  List<Course> findByStudents_Cpf(String cpf);
}

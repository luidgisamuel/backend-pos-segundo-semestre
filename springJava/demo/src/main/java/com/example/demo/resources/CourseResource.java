package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.utils.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/api")
@Api(value = "API REST Course")
@CrossOrigin (origins = "*")
public class CourseResource {
  @Autowired
  CourseRepository courseRepository;

  @GetMapping("/course")    
  public List<Course> listCourse(@RequestParam(required = false) String cpf_student) {
    if(cpf_student != null){
      return courseRepository.findByStudents_Cpf(cpf_student);
    }
    return courseRepository.findAll();
  }

  @GetMapping("/course/{id}")
  public Course listCourseById(@PathVariable(value = "id") Long id) {
    return courseRepository.findCourseById(id);
  }

  @PostMapping("/course")
  public ResponseEntity<Course> addCourse(@RequestBody @Valid Course course, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(201).body(courseRepository.save(course));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @DeleteMapping("/course/{id}")
  public ResponseEntity<Void> removeCourse(@PathVariable(value = "id") Long id, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      courseRepository.deleteById(id);
      return ResponseEntity.status(200).build();
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PutMapping("/course/{id}")
  public ResponseEntity<Course> updateCourse(@RequestBody @Valid Course course, @PathVariable(value = "id") Long id, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      Course course2 = courseRepository.findCourseById(id);
      course2.setId(id);
      course2.setName(course.getName());  
      course2.setTime_load(course.getTime_load());      
      course2.setSubjects(course.getSubjects());    
      return ResponseEntity.status(200).body(courseRepository.save(course2));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }
}

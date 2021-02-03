package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Course;
import com.example.demo.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    if(cpf_student !=null){
      return  courseRepository.findByStudents_Cpf(cpf_student);
    }
    return courseRepository.findAll();
  }

  @GetMapping("/course/{id}")
  public Course listCourseById(@PathVariable(value = "id") Long id) {
    return courseRepository.findCourseById(id);
  }

  @PostMapping("/course")
  public Course addCourse(@RequestBody @Valid Course course) {
    return  courseRepository.save(course);
  }

  @DeleteMapping("/course/{id}")
  public void removeCourse(@PathVariable(value = "id") Long id) {
    courseRepository.deleteById(id);
  }

  @PutMapping("/course/{id}")
  public Course updateCourse(@RequestBody @Valid Course course, @PathVariable(value = "id") Long id) {
    Course course2 = courseRepository.findCourseById(id);
    course2.setId(id);
    course2.setName(course.getName());  
    course2.setTime_load(course.getTime_load());      
    course2.setSubjects(course.getSubjects());    
    return courseRepository.save(course2); 
  }
}

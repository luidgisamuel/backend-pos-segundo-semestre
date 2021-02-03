package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Grade;
import com.example.demo.repository.GradeRepository;

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
@RequestMapping(value = "/api")
@Api(value = "API REST Grade")
@CrossOrigin(origins = "*")
public class GradeResource {

  @Autowired
  GradeRepository gradeRepository;

  @GetMapping("/grade")  
  public List<Grade> listCourse(@RequestParam(required = false) Long subject_id) {
    if(subject_id !=null){
      return  gradeRepository.findBySubject_Id(subject_id);
    }
    return gradeRepository.findAll();
  }

  @PostMapping("/grade")
  public Grade addGrade(@RequestBody @Valid Grade grade) {
    return  gradeRepository.save(grade);
  }

  @DeleteMapping("/grade/{id}")
  public void removeGrade(@PathVariable(value = "id") Long id) {
    gradeRepository.deleteById(id);
  }

  @PutMapping("/grade/{id}")
  public Grade updateGrade(@RequestBody @Valid Grade grade, @PathVariable(value = "id") Long id) {
    Grade grade2 = gradeRepository.findGradeById(id);
    grade2.setId(id);
    grade2.setDescription(grade.getDescription());  
    grade2.setValue(grade.getValue()); 
    grade2.setStudent(grade.getStudent()); 
    grade2.setSubject(grade.getSubject()); 
    return gradeRepository.save(grade2); 
  }
}

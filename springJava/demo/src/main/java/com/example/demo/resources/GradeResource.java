package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Grade;
import com.example.demo.repository.GradeRepository;
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
@RequestMapping(value = "/api")
@Api(value = "API REST Grade")
@CrossOrigin(origins = "*")
public class GradeResource {

  @Autowired
  GradeRepository gradeRepository;

  @GetMapping("/grade")
  public ResponseEntity<List<Grade>> listCourse(@RequestParam(required = false) Long subject_id, @RequestHeader("auth_token") String token) {
    if (Auth.validateUser(token, List.of("professor", "student"))) {
      if (subject_id !=null){
        return ResponseEntity.status(200).body(gradeRepository.findBySubject_Id(subject_id));
      }
      return ResponseEntity.status(200).body(gradeRepository.findAll());
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PostMapping("/grade")
  public ResponseEntity<Grade> addGrade(@RequestBody @Valid Grade grade, @RequestHeader("auth_token") String token) {
    if (Auth.validateUser(token, List.of("professor"))) {
      return ResponseEntity.status(201).body(gradeRepository.save(grade));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @DeleteMapping("/grade/{id}")
  public ResponseEntity<Void> removeGrade(@PathVariable(value = "id") Long id, @RequestHeader("auth_token") String token) {
    if (Auth.validateUser(token, List.of("professor"))) {
      gradeRepository.deleteById(id);
      return ResponseEntity.status(200).build();
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PutMapping("/grade/{id}")
  public ResponseEntity<Grade> updateGrade(@RequestBody @Valid Grade grade, @PathVariable(value = "id") Long id, @RequestHeader("auth_token") String token) {
    if (Auth.validateUser(token, List.of("professor"))) {
      Grade grade2 = gradeRepository.findGradeById(id);
      grade2.setId(id);
      grade2.setDescription(grade.getDescription());  
      grade2.setValue(grade.getValue()); 
      grade2.setStudent(grade.getStudent()); 
      grade2.setSubject(grade.getSubject()); 
      return ResponseEntity.status(200).body(gradeRepository.save(grade2));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }
}

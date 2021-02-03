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
  public List<Grade> listGrade() {   
    return gradeRepository.findAll();
  }

  @GetMapping("/grade/{id}")
  public Grade listGradeById(@PathVariable(value = "id") Long id) {
    return gradeRepository.findGradeById(id);
  }

  @PostMapping("/grade")
  public Grade addGrade(@RequestBody @Valid Grade grade) {
    return  gradeRepository.save(grade);
  }

  @DeleteMapping("/grade")
  public void removeGrade(@RequestBody @Valid Grade grade) {
    gradeRepository.delete(grade);
  }

  @PutMapping("/grade")
  public Grade updateGrade(@RequestBody @Valid Grade grade) {
    return gradeRepository.save(grade);
  }
}

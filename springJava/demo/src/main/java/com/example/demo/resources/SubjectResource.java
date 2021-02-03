package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Subject;
import com.example.demo.repository.SubjectRepository;

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
@RequestMapping(value="/api")
@Api(value = "API REST Institute")
@CrossOrigin (origins = "*")

public class SubjectResource {
  
  @Autowired
  SubjectRepository subjectRepository;

  @GetMapping("/subject")  
  public List<Subject> listSubject() {
    return subjectRepository.findAll();
  }

  @GetMapping("/subject/{id}")
  public Subject listSubjectById(@PathVariable(value = "id") Long id) {
    return subjectRepository.findSubjectById(id);
  }

  @PostMapping("/subject")
  public Subject addSubject(@RequestBody @Valid Subject subject) {
    return  subjectRepository.save(subject);
  }

  @DeleteMapping("/subject/{id}")
  public void removeSubject(@PathVariable(value = "id") Long id) {
    subjectRepository.deleteById(id);
  }

  @PutMapping("/subject/{id}")
  public Subject updateSubject(@RequestBody @Valid Subject subject, @PathVariable(value = "id") Long id) {
    Subject subject2 = subjectRepository.findSubjectById(id);
    subject2.setId(id);
    subject2.setName(subject.getName());  
    subject2.setTime_load(subject.getTime_load()); 
    return subjectRepository.save(subject2); 
  }
}

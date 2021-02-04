package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Subject;
import com.example.demo.repository.SubjectRepository;
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
  public ResponseEntity<List<Subject>> listSubject(@RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(200).body(subjectRepository.findAll());
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @GetMapping("/subject/{id}")
  public ResponseEntity<Subject> listSubjectById(@PathVariable(value = "id") Long id, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(200).body(subjectRepository.findSubjectById(id));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PostMapping("/subject")
  public ResponseEntity<Subject> addSubject(@RequestBody @Valid Subject subject, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(201).body(subjectRepository.save(subject));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @DeleteMapping("/subject/{id}")
  public ResponseEntity<Void> removeSubject(@PathVariable(value = "id") Long id, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      subjectRepository.deleteById(id);
      return ResponseEntity.status(200).build();
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PutMapping("/subject/{id}")
  public ResponseEntity<Subject> updateSubject(@RequestBody @Valid Subject subject, @PathVariable(value = "id") Long id, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      Subject subject2 = subjectRepository.findSubjectById(id);
      subject2.setId(id);
      subject2.setName(subject.getName());  
      subject2.setTime_load(subject.getTime_load()); 
      return ResponseEntity.status(200).body(subjectRepository.save(subject2));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }
}

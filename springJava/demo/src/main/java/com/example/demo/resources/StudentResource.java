package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Student;
import com.example.demo.repository.StudentRepository;
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
@Api(value = "API REST Student")
@CrossOrigin (origins = "*")

public class StudentResource {
  
  @Autowired
  StudentRepository studentRepository;

  @GetMapping("/student")
  public ResponseEntity<List<Student>> listStudent(@RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(200).body(studentRepository.findAll());
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @GetMapping("/student/{cpf}")
  public ResponseEntity<Student> listStudentById(@PathVariable(value = "cpf") String cpf, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(200).body(studentRepository.findByCpf(cpf));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PostMapping("/student")
  public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(201).body(studentRepository.save(student));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @DeleteMapping("/student/{cpf}")
  public ResponseEntity<Void> removeStudent(@PathVariable(value = "cpf") String cpf, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      studentRepository.deleteById(cpf);
      return ResponseEntity.status(200).build();
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PutMapping("/student/{cpf}")
  public ResponseEntity<Student> updateStudent(@RequestBody @Valid Student student, @PathVariable(value = "cpf") String cpf, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      Student student2 = studentRepository.findByCpf(cpf);
      student2.setCpf(cpf);
      student2.setName(student.getName());
      student2.setAddress(student.getAddress());
      student2.setCellphone(student.getCellphone());  
      student2.setUser(student.getUser());
      student2.setCourses(student.getCourses());
      return ResponseEntity.status(200).body(studentRepository.save(student2));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }
}

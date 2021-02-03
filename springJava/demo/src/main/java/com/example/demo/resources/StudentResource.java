package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Student;
import com.example.demo.repository.StudentRepository;

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
@Api(value = "API REST Student")
@CrossOrigin (origins = "*")

public class StudentResource {
  
  @Autowired
  StudentRepository studentRepository;

  @GetMapping("/student")  
  public List<Student> listStudent() {
    return studentRepository.findAll();
  }

  @GetMapping("/student/{cpf}")
  public Student listStudentById(@PathVariable(value = "cpf") String cpf) {
    return studentRepository.findByCpf(cpf);
  }

  @PostMapping("/student")
  public Student addStudent(@RequestBody @Valid Student student) {
    return  studentRepository.save(student);
  }

  @DeleteMapping("/student")
  public void removeStudent(@RequestBody @Valid Student student) {
    studentRepository.delete(student);
  }

  @PutMapping("/student")
  public Student updateStudent(@RequestBody @Valid Student student) {
    return studentRepository.save(student);
  }
}

package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Professor;
import com.example.demo.repository.ProfessorRepository;
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
@Api(value = "API REST Professor")
@CrossOrigin (origins = "*")
public class ProfessorResources {
  
  @Autowired
  ProfessorRepository professorRepository;

  @GetMapping("/professor")
  public ResponseEntity<List<Professor>> listProfessor(@RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(200).body(professorRepository.findAll());
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @GetMapping("/professor/{cpf}")
  public ResponseEntity<Professor> listProfessorById(@PathVariable(value = "cpf") String cpf, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(200).body(professorRepository.findByCpf(cpf));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PostMapping("/professor")
  public ResponseEntity<Professor> addProfessor(@RequestBody @Valid Professor professor, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(201).body(professorRepository.save(professor));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @DeleteMapping("/professor/{cpf}")
  public ResponseEntity<Void> removeProfessor(@PathVariable(value = "cpf") String cpf, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      professorRepository.deleteById(cpf);
      return ResponseEntity.status(200).build();
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PutMapping("/professor/{cpf}")
  public ResponseEntity<Professor> updateProfessor(@RequestBody @Valid Professor professor, @PathVariable(value = "cpf") String cpf, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      Professor professor2 = professorRepository.findByCpf(cpf);
      professor2.setCpf(cpf);
      professor2.setName(professor.getName());
      professor2.setEducation(professor.getEducation());
      professor2.setCellphone(professor.getCellphone());
      professor2.setSalary(professor.getSalary());
      professor2.setUser(professor.getUser());
      return ResponseEntity.status(200).body(professorRepository.save(professor2));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }
}

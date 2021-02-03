package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Professor;
import com.example.demo.repository.ProfessorRepository;

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
@Api(value = "API REST Professor")
@CrossOrigin (origins = "*")
public class ProfessorResources {
  
  @Autowired
  ProfessorRepository professorRepository;

  @GetMapping("/professor")  
  public List<Professor> listProfessor() {
    return professorRepository.findAll();
  }

  @GetMapping("/professor/{cpf}")
  public Professor listProfessorById(@PathVariable(value = "cpf") String cpf) {
    return professorRepository.findByCpf(cpf);
  }

  @PostMapping("/professor")
  public Professor addProfessor(@RequestBody @Valid Professor professor) {
    return  professorRepository.save(professor);
  }

  @DeleteMapping("/professor")
  public void removeProfessor(@RequestBody @Valid Professor professor) {
    professorRepository.delete(professor);
  }

  @PutMapping("/professor")
  public Professor updateProfessor(@RequestBody @Valid Professor professor) {
    return professorRepository.save(professor);
  }
}

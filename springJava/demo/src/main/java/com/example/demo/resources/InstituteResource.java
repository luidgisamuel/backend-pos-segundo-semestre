package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Institute;
import com.example.demo.repository.InstituteRepository;
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
@RequestMapping(value = "/api")
@Api(value = "API REST Institute")
@CrossOrigin(origins = "*")

public class InstituteResource {

  @Autowired
  InstituteRepository instituteRepository;

  @GetMapping("/institute")
  public ResponseEntity<List<Institute>> listInstitute(@RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(200).body(instituteRepository.findAll());
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PostMapping("/institute")
  public ResponseEntity<Institute> addInstitute(@RequestBody @Valid Institute institute, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      return ResponseEntity.status(201).body(instituteRepository.save(institute));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @DeleteMapping("/institute/{cnpj}")
  public ResponseEntity<Void> removeInstitute(@PathVariable(value = "cnpj") String cnpj, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      instituteRepository.deleteById(cnpj);
      return ResponseEntity.status(200).build();
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }

  @PutMapping("/institute/{cnpj}")
  public ResponseEntity<Institute> updateInstitute(@RequestBody @Valid Institute institute, @PathVariable(value = "cnpj") String cnpj, @RequestHeader("auth_token") String token) {
    if(Auth.validateUser(token, List.of("admin"))) {
      Institute institute2 = instituteRepository.findByCnpj(cnpj);
      institute2.setCnpj(cnpj);
      institute2.setName(institute.getName());
      institute2.setAddress(institute.getAddress());
      institute2.setTelephone(institute.getTelephone());
      return ResponseEntity.status(200).body(instituteRepository.save(institute2));
    }
    else {
      return ResponseEntity.status(401).build();
    }
  }
}

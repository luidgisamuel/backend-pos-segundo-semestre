package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Institute;
import com.example.demo.repository.InstituteRepository;

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
@Api(value = "API REST Institute")
@CrossOrigin(origins = "*")

public class InstituteResource {

  @Autowired
  InstituteRepository instituteRepository;

  @GetMapping("/institute")
  public List<Institute> listInstitute() {
    return instituteRepository.findAll();
  }

  @PostMapping("/institute")
  public Institute addInstitute(@RequestBody @Valid Institute institute) {
    return instituteRepository.save(institute);
  }

  @DeleteMapping("/institute/{cnpj}")
  public void removeInstitute(@PathVariable(value = "cnpj") String cnpj) {
    instituteRepository.deleteById(cnpj);
  }

  @PutMapping("/institute/{cnpj}")
  public Institute updateInstitute(@RequestBody @Valid Institute institute, @PathVariable(value = "cnpj") String cnpj) {
    Institute institute2 = instituteRepository.findByCnpj(cnpj);
    institute2.setCnpj(cnpj);
    institute2.setName(institute.getName());
    institute2.setAddress(institute.getAddress());
    institute2.setTelephone(institute.getTelephone());
    return instituteRepository.save(institute2); 
  }
}

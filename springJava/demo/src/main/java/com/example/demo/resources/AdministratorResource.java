package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Administrator;
import com.example.demo.repository.AdministratorRepository;

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
@Api(value = "API REST Administrator")
@CrossOrigin(origins = "*")
public class AdministratorResource {

  @Autowired
  AdministratorRepository administratorRepository;

  @GetMapping("/admin")
  public List<Administrator> listAdministrator() {
    return administratorRepository.findAll();
  }

  @GetMapping("/admin/{cpf}")
  public Administrator listAdministratorById(@PathVariable(value = "cpf") String cpf) {
    return administratorRepository.findByCpf(cpf);
  }

  @PostMapping("/admin")
  public Administrator addAdministrator(@RequestBody @Valid Administrator administrator) {
    return administratorRepository.save(administrator);
  }

  @DeleteMapping("/admin/{cpf}")
  public void removeAdministrator(@PathVariable(value = "cpf") String cpf) {
    administratorRepository.deleteById(cpf);
  }

  @PutMapping("/admin/{cpf}")
  public Administrator updateAdministrator(@RequestBody @Valid Administrator administrator,
      @PathVariable(value = "cpf") String cpf) {
    Administrator administrator2 = administratorRepository.findByCpf(cpf);
    administrator2.setCpf(cpf);
    administrator2.setName(administrator.getName());
    administrator2.setUser(administrator.getUser());
    return administratorRepository.save(administrator2);
  }
}

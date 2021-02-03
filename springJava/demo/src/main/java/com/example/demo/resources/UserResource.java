package com.example.demo.resources;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

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
@Api(value = "API REST User")
@CrossOrigin (origins = "*")

public class UserResource {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/user")  
  public List<User> listUser() {
    return userRepository.findAll();
  }

  @GetMapping("/user/{id}")
  public User listUserById(@PathVariable(value = "id") Long id) {
    return userRepository.findUserById(id);
  }

  @PostMapping("/user")
  public User addUser(@RequestBody @Valid User user) {
    return  userRepository.save(user);
  }

  @DeleteMapping("/user")
  public void removeUser(@RequestBody @Valid User user) {
    userRepository.delete(user);
  }

  @PutMapping("/user")
  public User updateUser(@RequestBody @Valid User user) {
    return userRepository.save(user);
  }  
}

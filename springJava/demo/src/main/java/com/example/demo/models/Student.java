package com.example.demo.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;

@Entity
@Table (name="student")
public class Student implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  private String cpf;
  
  @NotNull
  private String name;

  @NotNull
  private String cellphone;

  @NotNull
  private String address;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "student_user",
  joinColumns = {@JoinColumn(name = "student_cpf")},
  inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private User user;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "student_course",
  joinColumns = {@JoinColumn(name = "student_cpf")},
  inverseJoinColumns = {@JoinColumn(name = "course_id")})
  private List<Course> courses;

  public Student() {
  }

  public Student(String cpf, String name, String cellphone, String address, User user, List<Course> courses) {
    this.cpf = cpf;
    this.name = name;
    this.cellphone = cellphone;
    this.address = address;
    this.user = user;
    this.courses = courses;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCellphone() {
    return this.cellphone;
  }

  public void setCellphone(String cellphone) {
    this.cellphone = cellphone;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Course> getCourses() {
    return this.courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }
  
}

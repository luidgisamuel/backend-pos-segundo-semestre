package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
@Table (name="professor")
public class Professor implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  private String cpf;
  
  @NotNull
  private String name;

  @NotNull
  private String cellphone;

  @NotNull
  private String education;

  @NotNull
  private double salary;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "professor_user",
  joinColumns = {@JoinColumn(name = "professor_cpf")},
  inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private User user;

  public Professor() {
  }

  public Professor(String cpf, String name, String cellphone, String education, double salary, User user) {
    this.cpf = cpf;
    this.name = name;
    this.cellphone = cellphone;
    this.education = education;
    this.salary = salary;
    this.user = user;
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

  public String getEducation() {
    return this.education;
  }

  public void setEducation(String education) {
    this.education = education;
  }

  public double getSalary() {
    return this.salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}

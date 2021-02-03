package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
@Table (name="administrator")
public class Administrator implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  private String cpf;

  @NotNull
  private String name;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "administrator_user",
  joinColumns = {@JoinColumn(name = "admin_cpf")},
  inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private User user;


  public Administrator() {
  }

  public Administrator(String cpf, String name, User user) {
    this.cpf = cpf;
    this.name = name;
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

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }
 

}

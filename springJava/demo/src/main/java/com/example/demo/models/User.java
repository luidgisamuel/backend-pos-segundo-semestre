package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="user")
public class User implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private long id;

  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  private String type;

  public User() {
  }

  public User(long id, String email, String password, String type) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.type = type;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
}

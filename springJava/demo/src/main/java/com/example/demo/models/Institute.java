package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "institution")
public class Institute implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String cnpj;

  @NotNull
  private String name;

  @NotNull
  private String address;

  @NotNull
  private String telephone; 

  public Institute() {
  }

  public Institute(String cnpj, String name, String address, String telephone) {
    this.cnpj = cnpj;
    this.name = name;
    this.address = address;
    this.telephone = telephone;
  }

  public String getCnpj() {
    return this.cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTelephone() {
    return this.telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }
  
}

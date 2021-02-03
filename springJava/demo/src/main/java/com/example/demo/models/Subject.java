package com.example.demo.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="subject")
public class Subject implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  @NotNull
  private String name;
  
  @NotNull
  private int time_load;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subjects")
  @JsonIgnore
	private Set< Course> courses = new HashSet<>();


  public Subject() {
  }

  public Subject(long id, String name, int time_load, Set<Course> courses) {
    this.id = id;
    this.name = name;
    this.time_load = time_load;
    this.courses = courses;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTime_load() {
    return this.time_load;
  }

  public void setTime_load(int time_load) {
    this.time_load = time_load;
  }

  public Set<Course> getCourses() {
    return this.courses;
  }

  public void setCourses(Set<Course> courses) {
    this.courses = courses;
  }
  
}

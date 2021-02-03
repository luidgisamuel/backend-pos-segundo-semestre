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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
@Table (name="course")
public class Course implements Serializable{
  private static final long serialVersionUID = 1L;

  @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
@NotNull
  private String name;
  
  @NotNull
  private int time_load;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "course_subject",
  joinColumns = {@JoinColumn(name = "course_id")},
  inverseJoinColumns = {@JoinColumn(name = "subject_id")})
  private Set<Subject> subjects= new HashSet<>();  

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "courses")
  @JsonIgnore
	private Set< Student> students = new HashSet<>();


  public Course() {
  }

  public Course(long id, String name, int time_load, Set<Subject> subjects, Set<Student> students) {
    this.id = id;
    this.name = name;
    this.time_load = time_load;
    this.subjects = subjects;
    this.students = students;
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

  public Set<Subject> getSubjects() {
    return this.subjects;
  }

  public void setSubjects(Set<Subject> subjects) {
    this.subjects = subjects;
  }

  public Set<Student> getStudents() {
    return this.students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }
  
  }

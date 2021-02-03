package com.example.demo.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name="grade")
public class Grade implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  @NotNull
  private String description;

  @NotNull
  private double value;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "grade_student",
  joinColumns = {@JoinColumn(name = "grade_id")},
  inverseJoinColumns = {@JoinColumn(name = "student_cpf")})
  private Student student;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "grade_subject",
  joinColumns = {@JoinColumn(name = "grade_id")},
  inverseJoinColumns = {@JoinColumn(name = "subject_id")})
  private List<Subject> subjects;



  public Grade() {
  }

  public Grade(long id, String description, double value, Student student, List<Subject> subjects) {
    this.id = id;
    this.description = description;
    this.value = value;
    this.student = student;
    this.subjects = subjects;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getValue() {
    return this.value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public Student getStudent() {
    return this.student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public List<Subject> getSubjects() {
    return this.subjects;
  }

  public void setSubjects(List<Subject> subjects) {
    this.subjects = subjects;
  }
  
}

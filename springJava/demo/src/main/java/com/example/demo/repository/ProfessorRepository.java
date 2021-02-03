package com.example.demo.repository;

import com.example.demo.models.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
  Professor findByCpf(String cpf); 
}

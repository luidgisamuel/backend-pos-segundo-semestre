package com.example.demo.repository;

import com.example.demo.models.Administrator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, String> {
  Administrator findByCpf(String cpf); 
}

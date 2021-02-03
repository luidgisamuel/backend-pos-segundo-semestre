package com.example.demo.repository;

import com.example.demo.models.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository  extends JpaRepository<Institute, String>{
  Institute findByCnpj(String cnpj);
}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cita;

@Repository
public interface CitasRepository extends JpaRepository<Cita, Long>{

}

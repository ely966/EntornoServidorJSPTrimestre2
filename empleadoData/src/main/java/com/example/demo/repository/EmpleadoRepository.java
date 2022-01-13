package com.example.demo.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,long>{
	
}

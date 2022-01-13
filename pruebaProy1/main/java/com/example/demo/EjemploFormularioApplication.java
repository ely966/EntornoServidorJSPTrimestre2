package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Empleado;
import com.example.demo.repository.EmpleadoRepository;

@SpringBootApplication
public class EjemploFormularioApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjemploFormularioApplication.class, args);
	}
	
	@Bean
	CommandLineRunner initData(EmpleadoRepository repositorio) {
		return (args) -> {
			/*
			 * Empleado emp1 = new Empleado("José Pérez", "jose.perez@dominio.es",
			 * "656777888"); Empleado emp2 = new Empleado("María Sánchez",
			 * "maria.sanchez@dominio.es", "656111222"); Empleado emp3 = new
			 * Empleado("Miguel Rodríguez", "miguel.rodriguez@dominio.es", "656444555");
			 * 
			 * repositorio.save(emp1); repositorio.save(emp2); repositorio.save(emp3);
			 * 
			 * repositorio.findAll().forEach(System.out::println);
			 */
			
			repositorio.saveAll(
					Arrays.asList(new Empleado(1, "José Pérez", "jose.perez@dominio.es", "656777888"),
							new Empleado(2, "María Sánchez", "maria.sanchez@dominio.es", "656111222"),
							new Empleado(3, "Miguel Rodríguez", "miguel.rodriguez@dominio.es", "656444555")
							)
					);
		};
	}

}

package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;
import com.example.repository.EmpleadoRepository;

@Service("EmpleadoServiceMemory")
public class EmpleadoServiceMemory implements EmpleadoService{
private EmpleadoRepository repositorio;
	
	@Override
		public Empleado add(Empleado e) {
			return repositorio.save(e);
		}
	
	@Override
		public List<Empleado> findAll() {
		return repositorio.findAll();
	}
	//*ES option, es decir que si no pones orElse que queire decir que sino hay id de null, pues te fallara.**//
	@Override
	public Empleado findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
//	public Empleado findByIdd(long id) {
//		Empleado result = null;
//		boolean encontrado = false;
//		int i = 0;
//		while (!encontrado && i<repositorio.size()) {
//			if (repositorio.get(i).getId() == id) {
//				encontrado = true;
//				result = repositorio.get(i);
//			} else {
//				i++;
//			}
//		}
		
//		return result;
	//}
	
	public Empleado edit(Empleado e) {
		return repositorio.save(e);
	}
	//public List<Empleado> findAll() {
	//	return repositorio;
	//}
	//ctrl+shift+/
//	public Empleado findById(long id) {
//		Empleado result = null;
//		boolean encontrado = false;
//		int i = 0;
//		while (!encontrado && i<repositorio.size()) {
//			if (repositorio.get(i).getId() == id) {
//				encontrado = true;
//				result = repositorio.get(i);
//			} else {
//				i++;
//			}
//		}
//		
//		return result;
//	}
//	
//	public Empleado edit(Empleado e) {
//		boolean encontrado = false;
//		int i = 0;
//		while (!encontrado && i < repositorio.size()) {
//			if (repositorio.get(i).getId() == e.getId()) {
//				encontrado = true;
//				repositorio.remove(i);
//				repositorio.add(i, e);
//			} else {
//				i++;
//			}
//		}
//		
//		if (!encontrado)
//			repositorio.add(e);
//		
//		return e;
//	}
//	@PostConstruct
//	public void init() {
//		repositorio.addAll(
//				Arrays.asList(new Empleado(1, "José Pérez", "jose.perez@dominio.es", "656777888"),
//						new Empleado(2, "María Sánchez", "maria.sanchez@dominio.es", "656111222"),
//						new Empleado(3, "Miguel Rodríguez", "miguel.rodriguez@dominio.es", "656444555")
//						)
//				);
		
//	}
	
}


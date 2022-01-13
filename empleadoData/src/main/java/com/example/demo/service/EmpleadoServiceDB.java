package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Empleado;
import com.example.repository.EmpleadoRepository;
import com.example.service.EmpleadoService;

@Primary
@Service("EmpleadoServiceDB")
public class EmpleadoServiceDB implements EmpleadoService{
	@Autowired
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
	
	public Empleado edit(Empleado e) {
		return repositorio.save(e);
	}

	
}
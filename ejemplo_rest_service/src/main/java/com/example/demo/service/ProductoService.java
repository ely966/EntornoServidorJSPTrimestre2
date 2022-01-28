package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository productoRepositorio;
	
	public List<Producto> findAll() {
		return productoRepositorio.findAll();
	}
	
	public Producto findById(Long id) {
		return productoRepositorio.findById(id).orElse(null);
	}
	
	public Producto add(Producto p) {
		return productoRepositorio.save(p);
	}
	
	public Producto edit(Producto p, Long id) {
		if (productoRepositorio.existsById(id)) {
			p.setId(id);
			return productoRepositorio.save(p);
		} else {
			return null;
		}
	}
	
	public Producto delete (Long id) {
		if (productoRepositorio.existsById(id)) {
			Producto p = productoRepositorio.findById(id).get();
			productoRepositorio.deleteById(id);
			return p;
		} else {
			return null;
		}
	}

}

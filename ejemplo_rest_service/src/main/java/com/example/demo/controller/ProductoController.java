package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoService productoServicio;
	
	@GetMapping("/producto")
	public List<Producto> findAll() {
		return productoServicio.findAll();
	}
	
	@GetMapping("/producto/{id}") 
	public Producto getById(@PathVariable Long id) {
		return productoServicio.findById(id);
	}
	
	@PostMapping("/producto")
	public Producto add(@RequestBody Producto p) {
		return productoServicio.add(p);
	}
	
	@PutMapping("/producto/{id}")
	public Producto edit(@RequestBody Producto p, @PathVariable Long id) {
		return productoServicio.edit(p, id);
	}
	
	@DeleteMapping("/producto/{id}")
	public Producto delete(@PathVariable Long id) {
		return productoServicio.delete(id);
	}
	

}

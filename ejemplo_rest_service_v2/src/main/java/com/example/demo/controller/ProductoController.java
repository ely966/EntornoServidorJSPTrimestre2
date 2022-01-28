package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Producto>> findAll() {
		List<Producto> result = productoServicio.findAll();
		
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
	}
	
	@GetMapping("/producto/{id}") 
	public ResponseEntity<Producto> getById(@PathVariable Long id) {
		Producto result = productoServicio.findById(id);
		
		if (result == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
	}
	
	@PostMapping("/producto")
	public ResponseEntity<Producto> add(@RequestBody Producto p) {
		Producto saved = productoServicio.add(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@PutMapping("/producto/{id}")
	public ResponseEntity<Producto> edit(@RequestBody Producto p, @PathVariable Long id) {
		Producto result= productoServicio.edit(p, id);
		
		if (result == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
	}
	
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productoServicio.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}

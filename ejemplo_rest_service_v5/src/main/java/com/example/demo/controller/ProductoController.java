package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.error.ApiError;
import com.example.demo.error.ProductoNotFoundException;
import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import com.fasterxml.jackson.databind.JsonMappingException;

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
	public Producto getById(@PathVariable Long id) {
		Producto result = productoServicio.findById(id);
		
		if (result == null) {
			throw new ProductoNotFoundException(id);
		} else {
			return result;
		}
	}
	
	@PostMapping("/producto")
	public ResponseEntity<Producto> add(@RequestBody Producto p) {
		Producto saved = productoServicio.add(p);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@PutMapping("/producto/{id}")
	public Producto edit(@RequestBody Producto p, @PathVariable Long id) {
		Producto result= productoServicio.edit(p, id);
		
		if (result == null) {
			throw new ProductoNotFoundException(id);
		} else {
			return result;
		}
	}
	
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Producto result = productoServicio.delete(id);
		
		if (result == null) {
			throw new ProductoNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ExceptionHandler(ProductoNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(ProductoNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ApiError> handleJsonMappingException(JsonMappingException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.BAD_REQUEST);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
}

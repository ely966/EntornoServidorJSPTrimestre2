package com.example.demo.model;

import java.util.Objects;

import javax.validation.constraints.Min;

public class Producto {
	
	
	private long id =3;
	private String nombre;
	private Integer precio;
	@Min(0) 
	private Integer cantidad;

	
	
	public Producto( String nombre, Integer precio, @Min(0) Integer cantidad) {
		super();
		id++;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		
	}

	
	public Producto(long id, String nombre, Integer precio, @Min(0) Integer cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}


	public Producto() {
		super();
		id++;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	public long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio +  "]";
	}
	
	
	
	
	
	

}

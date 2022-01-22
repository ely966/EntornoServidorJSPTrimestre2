package com.example.demo.model;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

public class Usuario {

	
	@NotEmpty
	private String userName;
	@NotEmpty
	private String pass;
	private String nombre;
	private String direccion;
	private Integer telefono;
	public Pedido listadeProductos;
	
	

	
	public Usuario() {}

	public Usuario(@NotEmpty String userName, @NotEmpty String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}
	public Usuario(@NotEmpty String userName, @NotEmpty String pass, String nombre,
			 String direccion, Integer telefono) {
		super();
		this.userName = userName;
		this.pass = pass;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono=telefono;
	}


	public String getUserName() {
		return userName;
	}

	
	
	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	@Override
	public int hashCode() {
		return Objects.hash(userName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(userName, other.userName);
	}


	@Override
	public String toString() {
		return "Usuario [userName=" + userName + ", pass=" + pass + ", nombre=" + nombre + ", direccion=" + direccion
				+ "]";
	}
	
	


	
	
	

}

package com.example.DTO;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class PedidoDTO {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String direccion;
	public PedidoDTO() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}

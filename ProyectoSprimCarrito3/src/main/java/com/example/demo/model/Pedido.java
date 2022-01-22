package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;

public class Pedido {
	
	private long idPedido=4;
	private Usuario user;
	private String direccion;
	private List<Producto> listaProductos;
	
	
	public Pedido(Usuario user) {
		super();
		idPedido++;
		this.user = user;
		this.listaProductos = new ArrayList<>();
		this.direccion=direccion;
		
	}

	public Pedido() {
		super();
		idPedido++;
		this.user = user;
		this.direccion=direccion;
		this.listaProductos = new ArrayList<>();
		
	}
	public Pedido(Usuario user,String direccion,HashMap<Number, Number> lista) {
		super();
		idPedido++;
		this.user = user;
		this.direccion=direccion;
		this.listaProductos = new ArrayList<>();
		
	}
	

	public Pedido(Usuario user, String direccion) {
		super();
		idPedido++;
		this.user = user;
		this.direccion = direccion;
		this.listaProductos = new ArrayList<>();
	}
	
	

	public Pedido(long idPedido, Usuario user, String direccion, HashMap<Number, Number> listaProducto) {
		super();
		this.idPedido = idPedido;
		this.user = user;
		this.direccion = direccion;
		this.listaProductos = new ArrayList<>();
	}
	public Pedido(long idPedido, Usuario user, String direccion) {
		super();
		this.idPedido = idPedido;
		this.user = user;
		this.direccion = direccion;
		this.listaProductos = new ArrayList<>();
		
	}


	public long getIdPedido() {
		return idPedido;
	}
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	public void addProductoaLista(Producto produ) {
		listaProductos.add(produ);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPedido);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return idPedido == other.idPedido;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", user=" + user + ", listaProducto=" + listaProductos + "]";
	}
	
	
	
}

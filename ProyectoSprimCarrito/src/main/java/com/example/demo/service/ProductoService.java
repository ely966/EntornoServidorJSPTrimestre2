package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;

@Service
public class ProductoService {

	private List<Producto> productos = new ArrayList<>();
	
	//**Añadir productos**//
	public void add(Producto prod) {
		productos.add(prod);
		
	}
	//**Mostrar lista de productos**//
	public List<Producto> mostrarProd(){
		
		return productos;
	}
	//**Inicializacion**//
	public void init() {
		productos.addAll(
				Arrays.asList(new Producto(0,"Madalena",1,0),
						new Producto(1,"Leche", 2,0),
						new Producto(2,"Helado", 4,0),
						new Producto(3,"Patatas", (int) 1.5,0))
				);
	}
	
	public Producto mostrarPorId(long id) {
		boolean encontrado = false;
		Producto ProductoEncontrado =new Producto();
		int i=0;
		while(!encontrado && i<productos.size()) {
			if(productos.get(i).getId() ==id) {
				encontrado=true;
				ProductoEncontrado =productos.get(i);
				
			}else {
				//si no encuentra suba aumentando i
				i++;
			}
		}
		//**SI no lo encuentra**//
		if(encontrado ==false) {
			
		}
		return ProductoEncontrado;
		
	}
	//**Crear array de productos con solo id para crear pedidos**//
		public List<Producto> crearLista(){
			int i=0;
			Producto productoParaPedido=null;
			List<Producto> productosListado= new ArrayList();
			while(i<productos.size()) {
				//**añadimos l id al hashmasp
				productoParaPedido=productos.get(i);
				 productosListado.add(productoParaPedido);
				
					i++;
			}
			
			return productosListado;
		}
		//**Crear array de productos para crear pedidos, le llega el array con cantidades**//
		public List<Producto> crearListaProducto( Integer[] arrayCant){
					int i=0;
					Producto productoParaPedido=null;
					List<Producto> productosListado= new ArrayList();
					while(i<productos.size()) {
						//**añadimos l id al hashmasp
						productoParaPedido=productos.get(i);
						productoParaPedido.setCantidad(arrayCant[i]);
						 productosListado.add(productoParaPedido);
						
							i++;
					}
					return productosListado;
				}
		
		
		
		//**Clacular precio total**//
		public Integer precioTotal( Integer[] arrayCant){
			int i=0;
			int precioTotal=0;
			
			while(i<arrayCant.length) {
				//**añadimos l id al hashmasp
				precioTotal=precioTotal+arrayCant[i];
				
					i++;
			}
			return precioTotal;
		}
	
}

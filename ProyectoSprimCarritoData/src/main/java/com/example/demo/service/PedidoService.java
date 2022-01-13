package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.model.Pedido;
@Service
public class PedidoService {
	
	private List<Pedido> pedidos =new ArrayList<>();
	
	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private ProductoService servicioProducto;
	//**Inicien automaticamente**//
	
	@PostConstruct
	public void init() {

		Usuario usuarioMaria=servicioUsuario.comprobarporUser("maria");
		Usuario usuarioLuis=servicioUsuario.comprobarporUser("luis");

		Pedido pedido1=new Pedido(1,usuarioMaria, "Calle Mar n.32");
		Pedido pedido2=new Pedido(2,usuarioLuis, "Avenida ");
		Pedido pedido3=new Pedido(3,usuarioMaria, "Plazoleta Lunar n.3");
		servicioProducto.init();
		
		List<Producto>catalogo=servicioProducto.mostrarProd();
		Producto producto = null;
		
		List<Producto>productosDelPedido=servicioProducto.mostrarProd();
		for (int i=0; i< catalogo.size(); i++) {
			producto=catalogo.get(i);
			pedido1.addProductoaLista(producto);
			
		}
		
		
	
	
		
		pedidos.addAll(Arrays.asList(pedido1,pedido2,pedido3
						));
		
		
	}
	
	
	
	public List<Pedido> mostrarPedidos(Usuario user){
		List<Pedido> pedidosDeUser =new ArrayList<>();
		int i=0;
		while(i<pedidos.size()) {
			if(pedidos.get(i).getUser() ==user) {
				pedidosDeUser.add(pedidos.get(i));
				
			}else {
				//si no encuentra suba aumentando i
				i++;
			}
			
		}
		return pedidos;
		
	}
	

	public List<Pedido> mostrarPedidosPorNick (String user){
		List<Pedido> pedidosDeUser =new ArrayList<>();
		int i=0;
		List<Pedido> pe=pedidos;
		while(i<pedidos.size()) {
			
			
			String a= pedidos.get(i).getUser().getUserName();
			if(a.equals(user)) {
				pedidosDeUser.add(pedidos.get(i));
				
			}
			i=i+1;
			
			
		}
		return pedidosDeUser;
		
	}
	
	public Pedido mostrarPorId(int id) {
		
		boolean encontrado = false;
		Pedido pedEncontrado =new Pedido();
		int i=0;
		while(!encontrado && i<pedidos.size()) {
			if(pedidos.get(i).getIdPedido() == id) {
				encontrado=true;
				pedEncontrado =pedidos.get(i);
				
			}else {
				//si no encuentra suba aumentando i
				i++;
			}
		}
		//**SI no lo encuentra**//
		if(encontrado ==false) {
			
		}
		return pedEncontrado;
		
	}
	
	//**Editar pedido
	
	
	public Pedido editarPedido (Pedido ped){
		Pedido pedidoRecibido= ped;
		boolean encontrado = false;
		int i=0;
		while(!encontrado && i<pedidos.size()) {
			long pedid= pedidos.get(i).getIdPedido();
			if(pedid == pedidoRecibido.getIdPedido()) {
				//Recoger el usuario
				Usuario user=pedidos.get(i).getUser();
				pedidoRecibido.setUser(user);
				pedidos.remove(pedidos.get(i).getIdPedido());
				pedidos.add(pedidoRecibido);
				encontrado= true;
				
			}
			i=i+1;
			
		}
		return pedidoRecibido;
	}
	
	//**Mostrar el ultimo id del pedido**//
	public long ultimoId() {
		long idP=0;
		for (int a=0 ; a<pedidos.size();a++) {
			idP=pedidos.get(a).getIdPedido();
		}
		idP=idP+1;
		return idP;
	}
	
	
	//**Añadir un pedido nuevo **//
	public Pedido addPedido (long idNuevoPed,Usuario user,String direccion,List<Producto> lista){
		//crea el pedido nuevo
			Pedido pedidoNuevo=new Pedido(idNuevoPed,user,direccion);
			pedidoNuevo.setListaProductos(lista);
			//**COmprobamos si existe ya
			for(int i=0; i < pedidos.size();i++) {
				long pedid= pedidos.get(i).getIdPedido();
				if(pedid == pedidoNuevo.getIdPedido()) {
					pedidos.remove(i);
					pedidos.add(pedidoNuevo);
					return pedidoNuevo;
				}
				i++;
			}
			//**Si no existia**//
			
			pedidos.add(pedidoNuevo);
		return pedidoNuevo;
	}
	
	public void addPedidoconpedido (Pedido pedido){
		//crea el pedido nuevo
			
			pedidos.add(pedido);
			
	}
	
	//**Añadir un pedido nuevo **//
	public Pedido addPedidoSinAddPedidos (long idNuevoPed,Usuario user,String direccion,List<Producto> lista){
		//crea el pedido nuevo
			Pedido pedidoNuevo=new Pedido(idNuevoPed,user,direccion);
			pedidoNuevo.setListaProductos(lista);
			//**COmprobamos si existe ya
			for(int i=0; i < pedidos.size();i++) {
				long pedid= pedidos.get(i).getIdPedido();
				if(pedid == pedidoNuevo.getIdPedido()) {
					pedidos.remove(i);
					pedidos.add(pedidoNuevo);
					return pedidoNuevo;
				}
				i++;
			}
			//**Si no existia**//
			
			
		return pedidoNuevo;
	}
	
	//**Borrar Pedido**//
	public void borrarPedidoPorId(int id) {
		
		boolean encontrado = false;
		
		int i=0;
		while(!encontrado && i<pedidos.size()) {
			if(pedidos.get(i).getIdPedido() == id) {
				pedidos.remove(i);
				
			}else {
				//si no encuentra suba aumentando i
				i++;
			}
		}
		//**SI no lo encuentra**//
		if(encontrado ==false) {
			
		}
		
		
	}
	
	
	
	
	
}

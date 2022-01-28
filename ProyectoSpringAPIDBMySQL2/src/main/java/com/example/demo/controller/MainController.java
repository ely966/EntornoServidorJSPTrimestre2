package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ped_Prod_Cantida;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.Ped_Prod_CantidaRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.Ped_Prod_CantidaService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.error.PedidoNotFoundException;
import com.example.error.UsuarioNotFoundException;

@RestController
public class MainController {

	@Autowired
	private Ped_Prod_CantidaRepository repositorioPedProd;
	@Autowired
	private Ped_Prod_CantidaService repositorioServicePP;
	
	@Autowired
	private UsuarioService userServi;
	
	@Autowired
	private ProductoService serviProd;
	
	@Autowired
	private ProductoRepository repositorioProducto;
	
	@Autowired
	//private PedidoService servipedi;
	private PedidoRepository repositorioPedido;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PedidoService serviPedi;
	
	private int inicio=0;
	/**@GetMapping({""})
	public String inicio(Model model) {
		
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		return "portada";
		}
	**/
	
	/**Listar usuario**/
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> findAllUsuario(){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		List<Usuario> usuarios = userServi.findAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(usuarios);
		}
	}
	
	/**Listar pedidos**/
	@GetMapping("/pedido")
	public ResponseEntity<List<Pedido>> findAllPedido(){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		List<Pedido> pedidos = serviPedi.findAll();
		if(pedidos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(pedidos);
		}
	}

	
	/**Mostrar un pedido concreto por su ID**/
	@GetMapping("/pedido/{id}")
	public Pedido pedidoById(@PathVariable Long id) {
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Pedido pedidoMostrar=serviPedi.findById(id);
		if (pedidoMostrar == null) {
			throw new PedidoNotFoundException(id);
		}else {
			return pedidoMostrar;
		}
	}
	
	/**Mostrar un usuario concreto por su Id**/
	@GetMapping("/usuario/{userName}")
	public Usuario usuarioById(@PathVariable String userName) {
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Usuario usuarioMostrar = userServi.findByUserName(userName);
		if (usuarioMostrar == null) {
			throw new UsuarioNotFoundException(userName);
		}else {
			return usuarioMostrar;
		}
	}
	/**Listar los pedidos de un usuario por UserName**/
	@GetMapping("/pedidosByUsuario/{userName}")
	public ResponseEntity<List<Pedido>> pedidosByUser(@PathVariable String userName){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Usuario usuarioMostrar = userServi.findByUserName(userName);
		List<Pedido> pedidos = usuarioMostrar.getPedidos();
		if(pedidos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(pedidos);
		}
	}

	/**Añadir pedido**/
	
	/**Editar pedido**/
	
	/**Borrar pedido**/
	
	
	
}
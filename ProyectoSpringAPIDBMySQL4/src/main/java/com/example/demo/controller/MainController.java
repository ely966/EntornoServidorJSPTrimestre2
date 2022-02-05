package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.UsuarioDTO;
import com.example.demo.model.Ped_Prod_Cantida;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.Ped_Prod_CantidaRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.Ped_Prod_CantidaService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.PedidoServiceMemory;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.UsuarioServiceMemory;
import com.example.error.PedidoNotFoundException;
import com.example.error.UsuarioNotFoundException;
import com.example.error.UsuarioNotFoundIdException;

@RestController
public class MainController {

	
	@Autowired
	private Ped_Prod_CantidaRepository repositorioPedProd;
	@Autowired
	private Ped_Prod_CantidaService repositorioServicePP;
	
	@Autowired
	private UsuarioService userServi;
	
	@Autowired
	private UsuarioServiceMemory serviUserMemory;
	
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
	
	@Autowired
	private PedidoServiceMemory seviPediMemory;
	
	private int inicio=0;
	
/**Producto**/
	/**Mostrar Productos**/
	@GetMapping("/producto")
	public ResponseEntity<List<Producto>> findAllProducto(){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		List<Producto> productos = serviProd.findAll();
		if(productos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(productos);
		}
	}
/**Usuario**/	
	
	/**Listar usuario**/
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> findAllUsuario(){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		/**Recogo los usuarios*/
		List<Usuario> usuarios = userServi.findAll();
		/**Llevo la lista de usuario al metodo siguiente apra que solo recoja laspropiedades que interesa, apra mostrar el usuario solo sin pedidos **/
		List<UsuarioDTO>usuariosDTO= serviUserMemory.listarUsuarioSinPedidos(usuarios);
		if(usuariosDTO.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(usuariosDTO);
		}
	}

	/**Pedidos**/	
	/**Listar pedidos**/
	@GetMapping("/pedidos")
	public ResponseEntity<List<Pedido>> findAllPedido2(){
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
	
	/**Listar los pedidos de un usuario por UserName
	 @GetMapping("/pedidos/{userName}")
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
**/
	/**Añadir pedido**/ 
	@PostMapping("/pedido/add")
	public ResponseEntity<Pedido> pedidoAdd(@RequestBody Pedido pedidoNuevo){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		/**Desde la id, comprobamos que existe el usuario y recogemos el resultado (si existe sus datos/ si no existe, sera null)**/
		Usuario userIndicado = userServi.findById(pedidoNuevo.getUser().getId());
		/**Si el usuario no es null, lo añadimos al pedido y continuamos la creacion del pedido**/
		if(userIndicado != null ) {
			pedidoNuevo= seviPediMemory.addUserAndDireccion(pedidoNuevo, userIndicado, pedidoNuevo.getDireccion());
			/**Creacion de usuario**/
			pedidoNuevo= serviPedi.add(pedidoNuevo);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(pedidoNuevo);
		
		}else {
			/**Error de que el usuario no existe**/	
			throw new UsuarioNotFoundException(userIndicado.getId());
		}
		
	}
	
	
	
	
	
	/**Editar pedido**/
	@PutMapping("/pedidoEdit")
	public Pedido editarpedido (@RequestBody Pedido ped) {
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Pedido pedidoEditado = serviPedi.edit(ped);
		if (pedidoEditado == null) {
			pedidoEditado.setId(0);
			throw new PedidoNotFoundException(pedidoEditado.getId());
		}else {
			return pedidoEditado;
		}
	}
	
	/**Borrar pedido**/
	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<?> deletePedido (@PathVariable Long id){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		/**Antes hay que borrar sus Ped_prod **/
		/**Recogemos uniones en general**/
		List<Ped_Prod_Cantida> unionesGeneral=repositorioPedProd.findAll();
		/**Enviar la id de pedido y las uniones general,para que me traiga la lista de uniones del pedido**/
		/**Recuperar todas las uniones del pedido**/
		List<Ped_Prod_Cantida> unionesDelPedido=seviPediMemory.listaUnionesDeUnPedido(id, unionesGeneral);
		repositorioServicePP.deleteAllByPedido(unionesDelPedido);
		
		/**Borrar pedido**/
		Pedido pedidoEditado = serviPedi.delete(id);
		if (pedidoEditado == null) {
			throw new PedidoNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
/**Uniones**/
	
	
	/**Listas uniones de un Pedido**//**id de un pedido**/
	@GetMapping("/unionesPed/{id}")
	public ResponseEntity<List<Ped_Prod_Cantida>> findAllUniones(@PathVariable Long id){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		List<Ped_Prod_Cantida> uniones = repositorioServicePP.findAll();
		/**Enviar la id de pedido y las uniones general,para que me traiga la lista de uniones del pedido**/
		/**Recuperar todas las uniones del pedido**/
		List<Ped_Prod_Cantida> unionesDelPedido=seviPediMemory.listaUnionesDeUnPedido(id, uniones);
		if(unionesDelPedido.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(unionesDelPedido);
		}
	}
	/**Añadir union**/ 
	//@PostMapping("/unionAdd")
	//public ResponseEntity<Pedido> pedidoAdd(@RequestBody Ped_Prod_Cantida union){
	//	Pedido nuevoPedido= serviPedi.add(pedidoNuevo);
	//	return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
	//}
	
	/**Editar uniones**/
	@PutMapping("/uniones/{id}")
	public Ped_Prod_Cantida editarUnion (@RequestBody Ped_Prod_Cantida union) {
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Ped_Prod_Cantida unionEditada = repositorioServicePP.edit(union);
		if (unionEditada == null) {
			unionEditada.setId(0);
			throw new PedidoNotFoundException(union.getId());
		}else {
			return unionEditada;
		}
	}
	/**Borrar Union**/
	@DeleteMapping("/uniones/{id}")
	public ResponseEntity<?> deleteUnion (@PathVariable Long id){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Ped_Prod_Cantida union = repositorioServicePP.delete(id);
		if (union == null) {
			throw new PedidoNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
	
}
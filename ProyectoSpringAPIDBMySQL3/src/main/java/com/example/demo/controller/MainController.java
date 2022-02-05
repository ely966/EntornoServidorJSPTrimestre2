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


import com.example.demo.model.Ped_Prod_Cantida;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.Ped_Prod_CantidaRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.Ped_Prod_CantidaService;
import com.example.demo.service.Ped_Prod_CantidaServiceMemory;
import com.example.demo.service.PedidoService;
import com.example.demo.service.PedidoServiceMemory;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.error.PedidoNotFoundException;
import com.example.error.UnionNotFountIdException;
import com.example.error.UsuarioNotFoundException;
import com.example.error.UsuarioNotFoundIdException;

@RestController
public class MainController {

	@Autowired
	private Ped_Prod_CantidaRepository repositorioPedProd;
	@Autowired
	private Ped_Prod_CantidaService repositorioServicePP;
	@Autowired
	private Ped_Prod_CantidaServiceMemory repositServiMem;
	
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
	
	@Autowired
	private PedidoServiceMemory seviPediMemory;
	
	private int inicio=0;
	
	
	/**Listar usuario**/
	@GetMapping("/usuarios")
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
/**Pedidos**/	
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
	
	/**Listar los pedidos de un usuario por UserName**/
/**	@GetMapping("/pedidos/{userName}")
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
	@PostMapping("/pedidoAdd")
	public ResponseEntity<Pedido> pedidoAdd(@RequestBody Pedido pedidoNuevo){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Usuario userIndicado = userServi.findById(pedidoNuevo.getUser().getId());
		/**Si el usuario no es null, lo añadimos al pedido y continuamos la creacion del pedido**/
		if(userIndicado != null ) {
			pedidoNuevo= seviPediMemory.addUserAndDireccion(pedidoNuevo, userIndicado, pedidoNuevo.getDireccion());
			/**Creacion de usuario**/
			pedidoNuevo= serviPedi.add(pedidoNuevo);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(pedidoNuevo);
		
		}else {
			/**Error de que el usuario no existe**/	
			throw new UsuarioNotFoundIdException(userIndicado.getId());
		}
		
	
	}
	/**Editar pedido**/
	@PutMapping("/pedidoEdit")
	public Pedido editarpedido (@RequestBody Pedido ped) {
		if(inicio == 0) {/**Se inicie el init**/
			serviPedi.init();
			inicio=inicio+1;
			}
		/**Primero comprobamos que exista el usuario**/
		Usuario usuario = userServi.findById(ped.getUser().getId());
		if (usuario == null) {/**Si no existe**/
			throw new UsuarioNotFoundIdException(ped.getUser().getId());
		}else {
			/**EL usuario existe pues lo añadimos el usuario completo a pedido para evitar las restrincciones y tener toda las informacion del usuario**/
			ped.setUser(usuario);
			Pedido pedidoEditado = serviPedi.edit(ped);
			
			if (pedidoEditado == null) {
				pedidoEditado.setId(0);
				throw new PedidoNotFoundException(pedidoEditado.getId());
			}else {
				return pedidoEditado;
			}
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
		/**COmprobar si tenia uniones**/
		if (unionesDelPedido == null) {
			
		}else {/**Si tenia unione qu elas elimine**/
			repositorioServicePP.deleteAllByPedido(unionesDelPedido);
		}
		
		
		/**Ahora sin uniones, Borrar pedido**/
		Pedido pedidoEditado = serviPedi.delete(id);
		if (pedidoEditado == null) {
			throw new PedidoNotFoundException(id);
		} else {
			return ResponseEntity.noContent().build();
		}
		
	}
	
/**Uniones**/
	
	
	/**Listas uniones de un Pedido**//**id de un pedido**/
	@GetMapping("/pedido/unionesPed/{id}")
	public ResponseEntity<List<Ped_Prod_Cantida>> findAllUniones(@PathVariable Long id){
		/**Se inicie init**/
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		List<Ped_Prod_Cantida> uniones = repositorioServicePP.findAll();
		/**Enviar la id de pedido y las uniones general,para que me traiga la lista de uniones del pedido**/
		/**Recuperar todas las uniones del pedido**/
		List<Ped_Prod_Cantida> unionesDelPedido=repositServiMem.unionesDelPedido(id, uniones); /**id del pedido**/
		if(unionesDelPedido.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(unionesDelPedido);
		}
	}
	/**Añadir union**/ 
	@PostMapping("/pedido/unionAdd")
	public ResponseEntity<Ped_Prod_Cantida> pedidoAdd(@RequestBody Ped_Prod_Cantida union){
		/**Iniciar init**/
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		Pedido ped= serviPedi.findById(union.getPedido().getId());
		/**COmprobar si existe el pedido**/
		if (ped == null) {
			throw new PedidoNotFoundException(union.getPedido().getId());
		}else {
			/**El pedido existe**/
			/**COmprobamos si existe el producto**/
			Producto pro = serviProd.findById(union.getProdutoo().getId());
			if (serviProd.findById(union.getProdutoo().getId()) == null) {
				throw new PedidoNotFoundException(union.getPedido().getId());/**Crear error de producto**/
			}else {
				/**El producto tambien existe**/
				Ped_Prod_Cantida nuevaUnion = new Ped_Prod_Cantida(ped, pro);
				nuevaUnion.setCantidad(union.getCantidad());
				nuevaUnion= repositorioPedProd.save(nuevaUnion);
				return ResponseEntity.status(HttpStatus.CREATED).body(union);
				}
			}
		}
		
	/**Editar uniones**/
	@PutMapping("/pedido/unionesEdit")
	public ResponseEntity<Ped_Prod_Cantida> editarUnion (@RequestBody Ped_Prod_Cantida union) {
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		/**COmprobamos que exista la union**/
		if (repositorioPedProd.findById(union.getId()) == null) {
			throw new PedidoNotFoundException(union.getPedido().getId());
		}else {
			
			Ped_Prod_Cantida unionEditada = repositorioServicePP.edit(union);
			return ResponseEntity.status(HttpStatus.CREATED).body(unionEditada);
			}
		
		}
	
	/**Borrar Union**/
	@DeleteMapping("/pedidos/unionesDelet/{id}")
	public ResponseEntity<?> deleteUnion (@PathVariable Long id){
		if(inicio == 0) {
			serviPedi.init();
			inicio=inicio+1;
			}
		/**Comprobar que existe la union esta**/
		Ped_Prod_Cantida unionCOmpleta= repositorioServicePP.findById(id);
		if (unionCOmpleta == null) {
			throw new UnionNotFountIdException(id);
		}else {
			Ped_Prod_Cantida union = repositorioServicePP.delete(id);
			if (union == null) {
				throw new PedidoNotFoundException(id);
			} else {
				return ResponseEntity.noContent().build();
			}
		}
		
		
	}
	
	
}
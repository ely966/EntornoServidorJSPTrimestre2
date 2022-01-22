package com.example.demo.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
@Controller
public class MainController {
	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private PedidoService  servicioPedi;
	@Autowired
	private ProductoService servicioProducto;
	@Autowired
	private HttpSession session;
	
	
	
	//**Inicio. Lleva a la portada. Recogemos un usuario que luego tendra lso atributos del formulario y asi comprobar si el usuario existe en el siguiente control.**//
	@GetMapping({"/"})
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		//servicioProducto.init();
		return "portada";
	}
	
	//**Su verison post para evitar el pantallazo error**//
	@PostMapping({"/"})
	public String loginPost(Model model) {
		model.addAttribute("usuario", new Usuario());
		//servicioProducto.init();
		return "portada";
	}
	
	
	
	//**Comprueba que existe el usuario en el login**//
	@GetMapping({"/usuario/comprobar"})
	public String comprobarUser (@Valid @ModelAttribute("usuario") Usuario usuario,
			BindingResult bindingResult) {
		//**Si hay algun error, redirija a la portada**//
		if(bindingResult.hasErrors()) { 
			
			//response.sendRedirect("/");
			 return "portada";
		}
		//**COmprobar el usuario**//
		if(servicioUsuario.comprobar(usuario)) {
				
				String usuarioDelUser=usuario.getUserName();
				session.setAttribute("userSesion",usuarioDelUser);
				//**Crear un lemento que añada la id de pedido guardao ara mas adelante para que luego podamos añadir y editar"
				long idPed=servicioPedi.ultimoId();
				session.setAttribute("idPedi",idPed);
					return "seleccion";	 
			}
			
			return "portada";
		}
	//**Su verison post para evitar el pantallazo error**//
		@PostMapping({"/usuario/comprobar"})
		public String comprobarUser2(Model model) {
			//servicioProducto.init();
			return "portada";
		}
	
	//**Listar pedidos que corresponde al usuario**//
	@GetMapping({"/servicio/listarporUser"})
	public String listarPedPorUser (Model model, ServletResponse res) throws IOException {
		//**Recogemos la respuesta para comprobar, si no existe el usuario o session, redirijiremos a la portada**//
		HttpServletResponse response = (HttpServletResponse) res;
				
		//**COmprobamos que la session y el usuario exista**//
		if(session.getAttribute("userSesion") == null) {
			 response.sendRedirect("/");
			 return null;
		}else {
			String userName = (String)session.getAttribute("userSesion");
			
			//Usuario userr=servicioUsuario.comprobarporUser("userName");
			model.addAttribute("listaPedido", servicioPedi.mostrarPedidosPorNick(userName));
			return "listaPedidos";
		}
		
	}
	//**Su verison post para evitar el pantallazo error**//
	@PostMapping({"/servicio/listarporUser"})
	public String listarPedPorUser2 (Model model) {
		//servicioProducto.init();
		return "portada";
			}
	
	//**Si se desea crear un pedido, tendra primero que ver el catalogo**//
	//**Para ello mostraeros la lista de productos**//
	@GetMapping({"/servicio/mostrarCatalogo"})
	public String mostrarCatalogo (Model model,ServletResponse res) throws IOException {
		//**Recogemos la respuesta para comprobar, si no existe el usuario o session, redirijiremos a la portada**//
		HttpServletResponse response = (HttpServletResponse) res;
		
		//**Comprobamos que la session y el usuario exista**//
		if(session.getAttribute("userSesion") == null) {
			response.sendRedirect("/");
			 return null;
		}else {
			String userName = (String)session.getAttribute("userSesion");
			Producto productoo= new Producto();
			model.addAttribute("catalogo", servicioProducto.mostrarProd());
			model.addAttribute("productoo", productoo );
			
			return "catalogo";
			
		}
	}
	
	//**Su verison post para evitar el pantallazo error**//
	@PostMapping({"/servicio/mostrarCatalogo"})
	public String mostrarCatalogo2 (Model model) {
		//servicioProducto.init();
		return "portada";
				}
		
	
	
	
	//**Dirigir a la seleccion**//
	//Desde seleccion se dirige a crear pedido. Mostrar productos.
		@GetMapping({"/servicio/seleccion"})
		public String seleccion (Model model,ServletResponse res) throws IOException {
			//**Recogemos la respuesta para comprobar, si no existe el usuario o session, redirijiremos a la portada**//
			HttpServletResponse response = (HttpServletResponse) res;
			//**Comprobamos que la session y el usuario exista**//

			if(session.getAttribute("userSesion") == null) {
				response.sendRedirect("/");
				 return null;
			}else {
				//**Si el usuario existe, redirige a la seleccion**//
				return "seleccion";
				
			}
		}
		
		//**Su verison post para evitar el pantallazo error**//
		@PostMapping({"/servicio/seleccion"})
		public String seleccion2 (Model model) {
			//servicioProducto.init();
			return "portada";
					}
	
		
	//**Editar pedido**//
		//**Una vez seleccionado el pedido de la lista de pedidos, se mostrara la informacion del pedido y permitir editar cantidades y direccion**//
	@GetMapping({"/pedido/edit/{id}"})
	public String editarPedido(@PathVariable int id,Model model,ServletResponse res) throws IOException {
		//**Recogemos la respuesta para comprobar, si no existe el usuario o session, redirijiremos a la portada**//
		HttpServletResponse response = (HttpServletResponse) res;
				
		//**Comprobamos que la session y el usuario exista**//

		if(session.getAttribute("userSesion") == null) {
			response.sendRedirect("/");
			 return null;
		}else {
			//**Si el usuario existe, recogeremos el usuario de la session**//
			String userName = (String)session.getAttribute("userSesion");
			//**Buscaremos el pedido por su id**//
			Pedido pedConcreto= servicioPedi.mostrarPorId(id);
			model.addAttribute("pedido", pedConcreto);
			model.addAttribute("listaPedidoo",pedConcreto.getListaProductos());
			
			
			return "editPedido";
		}
	}
	
	//**Su verison post para evitar el pantallazo error**//
	@PostMapping({"/pedido/edit/{id}"})
	public String editarPedido2 (Model model) {
		//servicioProducto.init();
		return "portada";
				}
	


	//
	//**Agregar el nuevo pedido con atributos editados**//

	
	//**Ya indicado en el anteior formulario, cual es el pedido que se va ha editar y haya editado el pedido, ahora lo agregamos**//

	@PostMapping({"/pedido/edit/submit"})
	public String editarPedidoNuevo(@Valid @ModelAttribute("pedido") Pedido pedido,@RequestParam(required=false,name=("cantidad"))Integer[] listaCantidad ,Model model, ServletResponse res) throws IOException   {
		
			//**Comprobamos que la session y el usuario exista**//

			if(session.getAttribute("userSesion") == null) {//**SI no existe**//
				
				return "portada";
			}else {
				//**Si el usuario y session existe**//
			//**Primero creamos una lista de integer, donde recogeremos al lista de integer recogida de las cantidades seleccionada del formulario**//
				Integer[] listDeCantidades =listaCantidad;
				//**Crearemos una lista apra añadir los productos**//
				List<Producto>productosDelPedido= new ArrayList();
				//**Añadiremos en el hashMap los id de todos los idproductos y las cantidades que traemos del formulario del catalogo**//
				//**Para ello mandaremos la lista de cantidades a un metodo al productoService**//
				//**En este servidor recogera una lista de productos donde añadira los productos. y añadira a cada uno la cantidad seleccionad ane el formulario**//
				productosDelPedido=servicioProducto.crearListaProducto(listDeCantidades);
				//**Recogeremos el usuario de la session**//
				String user = (String)session.getAttribute("userSesion");
				Usuario usuario =servicioUsuario.comprobarporUser(user);
				//**Agregamos los datos al pedido**//
				pedido.setUser(usuario);
				pedido.setListaProductos(productosDelPedido);
				//**En esta edicion borraremos el antiguo, y añadiremos el mismo pero con los dtaos cambiado**//
				servicioPedi.editarPedido(pedido);
				//**Redirige a la lista de usuario**//
				return "redirect:/servicio/listarporUser";
				}
		}
	//**Tiene su get para corregir errores, es decir, si la session esta vacia , para corregurla necesitamos su version get, para que no salte error y podamos redirigirlo**//
	@GetMapping({"/pedido/edit/submit"})
	public String editarPedidoNuevo(Model model, ServletRequest req,ServletResponse res) throws IOException   {
		
		//**Recogemos la respuesta. tambien recogi la peticion request por si acaso.
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//comprobamos si la session existe. Si no existe
		if(session.getAttribute("userSesion") == null) {
			response.sendRedirect("/");
			 return null;
		}else {
		
		return "seleccion";
		}
	}
	
	
		
		//**Borrar pedido**//
		@GetMapping({"/pedido/borrar/{id}"})
		public String borrarPedido(@PathVariable int id,Model model,ServletResponse res) throws IOException {
			//**Recogemos la respuesta.**//
			HttpServletResponse response = (HttpServletResponse) res;
			//comprobamos si la session existe. Si no existe
			if(session.getAttribute("userSesion") == null) {
				response.sendRedirect("/");
				 return null;
			}else {
				String userName = (String)session.getAttribute("userSesion");
				//Pedido pedConcreto= servicioPedi.mostrarPorId(id);
				//**Una vez recogido el producto, lo borramos**//
				servicioPedi.borrarPedidoPorId(id);
				
				
				return "redirect:/servicio/listarporUser";
			}
			
		}
		
		//**Su verison post para evitar el pantallazo error**//
				@PostMapping({"/pedido/borrar/{id}"})
				public String  borrarPedido (Model model) {
					//servicioProducto.init();
					return "portada";
							}
		
		
		
		
		
		//**Añadir un nuevo pedido1. Primera parte que viene del catalogo**//
		
		@GetMapping({"/pedido/add/subm"})
		public String addNew(Model model, ServletRequest req, ServletResponse res) throws IOException {
			
			//**Recogemos la respuesta. tambien recogi la peticion request por si acaso.
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			//comprobamos si la session existe. Si no existe
			
			if(session.getAttribute("userSesion") == null) {
				//**redirige a la portada**//
				response.sendRedirect("/");
				 return null;
			}else {
				//**Si todo esta correcto, iremos a comprobar el pedido**//
			return "comprobarPedido";
			}
		}
		
		
		//**Siguiente parte de añadir un nuevo pedido**//

			
		@PostMapping({"/pedido/add/subm"})
		public String addNew( @RequestParam(required=false,name=("cantidad"))Integer[] listaCantidad , Model model, ServletRequest req, ServletResponse res) {
			
			
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			
			if(session.getAttribute("userSesion") == null) {
				//response.sendRedirect("/");
				 return null;
			}else {
				//**Recoger todas las cantidades que esta en una lista, lo guardamos en otra lista
				Integer[] listDeCantidades =listaCantidad;
				//**Recoger las id de los productos**//
				//List<Producto> catalogo=servicioProducto.mostrarProd();
				
				//**Creamos un array para los productos del pedido**//
				//HashMap<Number,Number> productosSeleccionado= new HashMap<Number,Number> ();
				List<Producto>productosDelPedido= new ArrayList();
			//**Añadiremos en el hashMap los id de todos los idproductos y las cantidades que traemos del formulario del catalogo**//
				//**Para ello mandaremos la lista de cantidades a un metodo al productoService**//
				productosDelPedido=servicioProducto.crearListaProducto(listDeCantidades);
				
				//**Ahora recogeremos el username del usuario de session y asi obtendremos el usuario y direccion
				//**Recogemos el username de session y de ahi el usuario**//
				String userName = (String)session.getAttribute("userSesion");
				Usuario userr=servicioUsuario.comprobarporUser(userName);
				//Recoger direccion
				String direccion=userr.getDireccion();
				//**Para calcular el id**//
				//**COmo me falla el aumento automatico del id de pedido, lo envie a session y ahroa lo recogemos**//
				long idNuevoPed=(long) session.getAttribute("idPedi");
				//**AHora le sumaremos uno al valor del id que guarda session, para que tenga el nuevo id del nuevo producto que haga en la siguiente ocasion**//
				long nuevoValor=(long) session.getAttribute("idPedi") +1;
				session.setAttribute("idPedi",nuevoValor);
				Pedido pedidoNuevo=servicioPedi.addPedidoSinAddPedidos(idNuevoPed, userr, direccion, productosDelPedido);
				model.addAttribute("pedido",pedidoNuevo);
				model.addAttribute("listaProductos",pedidoNuevo.getListaProductos());
				model.addAttribute("precioTotal",servicioProducto.precioTotal(listaCantidad));
			
				
			return "comprobarPedido";
			}
		}
		
		
		
		
		//UNa vez comprobado el pedido se añadira a la lista de pedido""
		@PostMapping({"/pedido/addList"})
		public String addNewAlaListapedidos(@Valid @ModelAttribute("pedido") Pedido pedido,@RequestParam(required=false,name=("cantidad"))Integer[] listaCantidad , BindingResult binding) {
		
			if(binding.hasErrors()) { 
				//**Sino existe**//

				return "redirect:/";

			
			}else {
				Pedido a =pedido;
			//**Mostrar mejor los productos**//
				//Recoger el usuario
				Integer[] listDeCantidades =listaCantidad;
				List<Producto>productosDelPedido= new ArrayList();
			//**Añadiremos en el hashMap los id de todos los idproductos y las cantidades que traemos del formulario del catalogo**//
			//**Para ello mandaremos la lista de cantidades a un metodo al productoService**//
			productosDelPedido=servicioProducto.crearListaProducto(listDeCantidades);
			String user = (String)session.getAttribute("userSesion");
			Usuario usuario =servicioUsuario.comprobarporUser(user);
			pedido.setUser(usuario);
			pedido.setListaProductos(productosDelPedido);
			
			servicioPedi.addPedidoconpedido(pedido);
				
				
			return "redirect:/servicio/seleccion";
			}
		}

		@GetMapping({"/pedido/addList"})
		public String addNewAlaListapedidos (Model model, ServletResponse res) throws IOException {
			//**Recogemos la respuesta. tambien recogi la peticion request por si acaso.
			HttpServletResponse response = (HttpServletResponse) res;
			//comprobamos si la session existe. Si no existe
			
			if(session.getAttribute("userSesion") == null) {
				//**redirige a la portada**//
				response.sendRedirect("/");
				 return null;
			}else {
				//**Si todo esta correcto, iremos a comprobar el pedido**//
			return "comprobarPedido";
			}
		}
	
	
}

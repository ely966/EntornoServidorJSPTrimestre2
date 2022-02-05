package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ped_Prod_Cantida;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.Ped_Prod_CantidaRepository;

@Service("PedidoServiceMemory")
public class PedidoServiceMemory implements PedidoService {
	
	
	
	private List<Pedido> repositorioPedido = new ArrayList<>();
	@Autowired
	private Ped_Prod_CantidaRepository repositorioPedProd;
	@Autowired
	private Ped_Prod_CantidaService repositorioServicePP;
	@Autowired
	private UsuarioService userServi;
	@Autowired
	private ProductoService serviProd;
	
	@Override
	public Pedido add (Pedido ped) {
		boolean find =false;
		for(int i=0; i < repositorioPedido.size();i++) {
			if (repositorioPedido.get(i).equals(ped)) {
				find=true;
			}
		}
		if(find == false) {
		repositorioPedido.add(ped);
		}
		return ped;
	}
	@Override
	public List<Pedido> findAll(){
		return repositorioPedido;
	}
	@Override
	public Pedido findById(Long id) {
		Pedido pedido=null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < repositorioPedido.size()) {
			if (repositorioPedido.get(i).getId() == id) {
				encontrado = true;
				pedido=repositorioPedido.get(i);
			} else {
				i++;
			}
		}
		return pedido;
	}
		
	@Override
	public Pedido edit (Pedido ped) {
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < repositorioPedido.size()) {
			if (repositorioPedido.get(i).getId() == ped.getId()) {
				encontrado = true;
				repositorioPedido.remove(i);
				repositorioPedido.add(i, ped);
			} else {
				i++;
			}
		}
		
		if (!encontrado)
			repositorioPedido.add(ped);
		
		return ped;
	}
	
	//@Override
	//public List<Pedido> findByUsuario(Usuario user){
	//	List<Pedido> pedidos = null;
		//pedidos.add(repositorioPedido.findByUsuario(user).orElse(null));
	//	return pedidos;
	//}
	@Override
	public void init() {
		
		Usuario usuarioMaria=userServi.findByUserName("maria");
		Usuario usuarioLuis=userServi.findByUserName("luis");

		Pedido pedido1=new Pedido(usuarioMaria, "Calle Mar n.32");
		Pedido pedido2=new Pedido(usuarioLuis, "Avenida ");
		Pedido pedido3=new Pedido(usuarioMaria, "Plazoleta Lunar n.3");
		
		//**Editamos los usuarios añadiendole slos pedidso**//
		usuarioMaria.getPedidos().add(pedido1);
		usuarioMaria.getPedidos().add(pedido3);
		usuarioLuis.getPedidos().add(pedido2);
		
		userServi.edit(usuarioLuis);
		userServi.edit(usuarioMaria);
		
		//**Modificamos el usuario añadido en los pedidos**//
		
		List<Producto>catalogo=serviProd.findAll();
		Producto producto = null;
		
		List<Producto>productosDelPedido=serviProd.findAll();
		repositorioServicePP.init(catalogo, pedido1, pedido3, pedido2);
		
		
		repositorioPedido.addAll(Arrays.asList(pedido1,pedido2,pedido3
						));
		
	}
	@Override
	public List<Pedido> findByUsuario(Usuario user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido delete(Long id) {
		Boolean encontrado =false;
		int i=0;
		while ( i<repositorioPedido.size() && !encontrado) {
			if(repositorioPedido.get(i).getId() == id) {
				Pedido ped = repositorioPedido.get(i);
				repositorioPedido.remove(i); 
				encontrado =true;
				return ped;
			}
			else {
				i=i+1;
			}
			}
	return null;
		
	}
	
	public List<Ped_Prod_Cantida> listaUnionesDeUnPedido(Long id,List<Ped_Prod_Cantida> unionesGeneral){
		/**Recogemos la lista de uniones del pedido**/
		List<Ped_Prod_Cantida> unionesDelPedido = null;
		for (int i=0; i< unionesGeneral.size();i++) {
			if(unionesGeneral.get(i).getPedido().getId() == id) {
				unionesDelPedido.add(unionesGeneral.get(i));
			}
		}
		return unionesDelPedido;
	}
	
	
	/**Mostrar un pedido
	
	/**Añadir datos usuario y direccion**/
	public Pedido addUserAndDireccion(Pedido pedido, Usuario user, String direccion) {
		pedido.setUser(user);
		pedido.setDireccion(direccion);
		return pedido;
	}
	
}


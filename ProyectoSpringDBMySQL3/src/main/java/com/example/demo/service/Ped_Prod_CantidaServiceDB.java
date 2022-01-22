package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ped_Prod_Cantida;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.repository.Ped_Prod_CantidaRepository;
import com.example.demo.repository.PedidoRepository;

@Primary
@Service("Ped_Prod_CantidaServiceDB")
public class Ped_Prod_CantidaServiceDB implements  Ped_Prod_CantidaService{

	@Autowired
	private Ped_Prod_CantidaRepository repositorioPedProd;
	
	@Autowired
	private PedidoRepository repositorioPedido;
	
	
	
	
	
	@Override
	public Ped_Prod_Cantida add(Ped_Prod_Cantida pedidoProd) {
		return repositorioPedProd.save(pedidoProd);
	}
	@Override
	public Ped_Prod_Cantida findById(long id) {
		return repositorioPedProd.findById(id).orElse(null);
	}
	@Override
	public List<Ped_Prod_Cantida> findAll(){
		return repositorioPedProd.findAll();
	}
	@Override
	public Ped_Prod_Cantida edit(Ped_Prod_Cantida pedidoProd) {
		return repositorioPedProd.save(pedidoProd);
	}
	
	@Override
	public void init(List<Producto>catalogo,Pedido p1, Pedido p2, Pedido p3) {
		//**Añadir las uniones**//
		for (int i=0; i< catalogo.size(); i++) {
			Producto producto=catalogo.get(i);
			//**De cada Producto, lo unimos a cada pedido**//
			Ped_Prod_Cantida pedProd= new Ped_Prod_Cantida(p1,catalogo.get(i),0);
			Ped_Prod_Cantida pedProd1= new Ped_Prod_Cantida(p2,catalogo.get(i),0);
			Ped_Prod_Cantida pedProd2= new Ped_Prod_Cantida(p3,catalogo.get(i),0);
			
			repositorioPedProd.save(pedProd);
			repositorioPedProd.save(pedProd1);
			repositorioPedProd.save(pedProd2);
					
					}
		}
	@Override
	public void delete(long id) {
		repositorioPedProd.deleteById(id);
	}
	
	
	
}

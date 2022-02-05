package com.example.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;

public class PedidosDTOconverter {
	public List<PedidoDTO> pedidosDTO= new ArrayList<PedidoDTO>();

	public List<PedidoDTO> converteTo(List<Pedido> pedidos){
		
		for (int i=0; i< pedidos.size();i=i+1) {
			PedidoDTO pedidoDTO=new PedidoDTO();
			pedidoDTO.setId(pedidos.get(i).getId());
			pedidoDTO.setDireccion(pedidos.get(i).getDireccion());
			pedidosDTO.add(pedidoDTO);
		}
		return pedidosDTO;
	}
	
	public PedidoDTO converteToOnePedido(Pedido pedido){
		PedidoDTO pedidoDTO=new PedidoDTO();
		pedidoDTO.setId(pedido.getId());
		pedidoDTO.setDireccion(pedido.getDireccion());
		pedidosDTO.add(pedidoDTO);
		return pedidoDTO;
	}

}

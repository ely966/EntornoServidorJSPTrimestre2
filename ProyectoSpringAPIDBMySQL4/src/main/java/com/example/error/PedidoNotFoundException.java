package com.example.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8999988807858451527L;

	public PedidoNotFoundException(Long id) {
		super("No se puede encontrar el pedido con la id :" + id);
	}
}

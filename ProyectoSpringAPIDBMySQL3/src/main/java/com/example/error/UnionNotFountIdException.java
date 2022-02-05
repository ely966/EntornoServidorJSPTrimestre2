package com.example.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnionNotFountIdException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3756565161639073807L;

	public UnionNotFountIdException(long id) {
		super("No se puede encontrar la union Pedido-Producto con este id " + id);
	}
}

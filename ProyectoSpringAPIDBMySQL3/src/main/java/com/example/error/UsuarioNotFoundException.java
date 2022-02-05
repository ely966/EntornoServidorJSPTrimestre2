package com.example.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7138341050110068386L;

	public UsuarioNotFoundException(String userName) {
		super("No se puede encontrar el usuario con el username " + userName);
	}
}

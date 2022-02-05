package com.example.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundIdException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6089034682633809938L;

	/**
	 * 
	 */
	

	public UsuarioNotFoundIdException(Long id) {
		super("No se puede encontrar el usuario con el username " + id);
	}
}


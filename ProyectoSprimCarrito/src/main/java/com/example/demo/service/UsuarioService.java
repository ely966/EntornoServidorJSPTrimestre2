package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;

@Service
public class UsuarioService {
	
	private List<Usuario> usuarios= new ArrayList<>();
	
	
	
	//**COmprobar si el usuario Existe**//
		public boolean comprobar(Usuario user) {
			boolean encontrado = false;
			int i=0;
			while(!encontrado && i<usuarios.size()) {
				if(Objects.equals(usuarios.get(i).getUserName(), user.getUserName())) {
					//**Comprobamos tambien que tenga la contraseña correcta**//
					if(Objects.equals(usuarios.get(i).getPass(), user.getPass())) {
						encontrado=true;
					};
				}else {
					//si no encuentra suba aumentando i
					i++;
				}
			}
			return encontrado;
			}
	//**Comprobar con nombre usuario**//
		
		public Usuario comprobarporUser(String user) {
			boolean encontrado = false;
			Usuario usuarioEncontrado = new Usuario();
			int i=0;
			while(!encontrado && i<usuarios.size()) {
				if(Objects.equals(usuarios.get(i).getUserName(), user)) {
					encontrado=true;
					usuarioEncontrado=usuarios.get(i);
					
				}else {
					//si no encuentra suba aumentando i
					i++;
				}
			}
			//**SI no lo encuentra**//
			if(encontrado ==false) {
				
			}
			return usuarioEncontrado;
			}
		
	//**Añadir usuarios a la lista **//
	//si no esta en el array//
	public void add(Usuario user) {
		boolean encontrado = false;
		int i=0;
		while(!encontrado && i<usuarios.size()) {
			if(Objects.equals(usuarios.get(i).getUserName(), user.getUserName())) {
				encontrado=true;
			}else {
				//si no encuentra suba aumentando i
				i++;
			}
		}
		//si no esta en array, añadelo
		if(encontrado == false) {
			usuarios.add(user);
			
		}
		
	}
	
		
	
	//**Mostrar lista**//
	public List<Usuario> mostrar(){
		return usuarios;
	}
	
	//**Inicializacion con 2 usuarios**//
	@PostConstruct
	public void init() {
		//UsuarioService servicioUser = null;
		usuarios.addAll(Arrays.asList(
				new Usuario("luis","luis","Luis Morales","Calle Mares n.6 piso:2A",654234187),
				new Usuario("maria","maria","Maria Rodriguez", "Avenida MariaFlores n.2 piso:4B",663123563)
				));
	}
	
	//**COmprobar si el  usuario existe entonces no añadir**//
	
	public void comprobarExiste(String nombre,String pass) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

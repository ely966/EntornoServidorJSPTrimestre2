package com.example.DTO;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Usuario;

public class UsuariosDTOConverter {

public List<UsuarioDTO> usuariosDtos= new ArrayList<UsuarioDTO>();
	
	public UsuariosDTOConverter() {
	super();
}

	public List<UsuarioDTO> converteTo(List<Usuario> usuarios){
		
		for (int i=0; i< usuarios.size();i=i+1) {
			UsuarioDTO user=new UsuarioDTO();
			user.setId(usuarios.get(i).getId());
			user.setUserName(usuarios.get(i).getUserName());
			user.setNombre(usuarios.get(i).getNombre());
			user.setPass(usuarios.get(i).getPass());
			user.setDireccion(usuarios.get(i).getDireccion());
			user.setTelefono(usuarios.get(i).getTelefono());
			usuariosDtos.add(user);
		}
		return usuariosDtos;
	}
	
	public UsuarioDTO converteToOneUser(Usuario usuario){
		
		UsuarioDTO userDto=new UsuarioDTO();
		
		userDto.setId(usuario.getId());
		userDto.setUserName(usuario.getUserName());
		userDto.setNombre(usuario.getNombre());
		userDto.setPass(usuario.getPass());
		userDto.setDireccion(usuario.getDireccion());
		userDto.setTelefono(usuario.getTelefono());
		return userDto;
	}
}

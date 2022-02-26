package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.error.MascotaExistedException;
import com.example.demo.model.Mascota;
import com.example.demo.model.User;
import com.example.demo.repository.MascotaRepository;

@Primary
@Service("MascotaService")
public class MascotaService {
	
	@Autowired private MascotaRepository mascotaRepo;
	

	public Mascota addMascota(Mascota mascota, User cliente) {
		Mascota newMascota= new Mascota();
		newMascota.setNombre(mascota.getNombre());
		newMascota.setTipo(mascota.getTipo());
		newMascota.setRaza(mascota.getRaza());
		newMascota.setEdad(mascota.getEdad());
		newMascota.setUsuario(cliente);
		mascotaRepo.save(newMascota);
		List<Mascota>pets=mascotaRepo.findAll();
		return newMascota;
	}
	public Mascota encontrarId(Long id) {
		Mascota mascotaseleccionada = mascotaRepo.findById(id).get();
		return mascotaseleccionada;
		
	}
	public Mascota delete (Mascota mascota) {
		if(mascotaRepo.existsById(mascota.getId())) {
			mascotaRepo.deleteById(mascota.getId());
			return mascota;
		}
		else {
			throw new IllegalArgumentException("\"La mascota no existe, y por lo tanto no puede ser borrada\"");
		}
	}
	
	public void mostrarMascotaPorUsuario(User cliente){
		List<Mascota> mascotasGeneral =mascotaRepo.findAll();
		List<Mascota> mascotasUser=new ArrayList();
		
		for (int i =0; mascotasGeneral.size() > i ; i=i+1) {
			if(mascotasGeneral.get(i).getUsuario().equals(cliente)) {
				mascotasUser.add(mascotasGeneral.get(i));
			}
		}
		
	}
	/** 
	 * Metodo para buscar las mascotas de un cliente
	 * @param cliente
	 * @return lista de las mascotas que e pertenece al cliente
	 */
	
	public List<Mascota> mostrarMascotadeUser(User cliente){
		List<Mascota> mascotasUser=cliente.getMascotas();
		return mascotasUser;
	}
	
	
}

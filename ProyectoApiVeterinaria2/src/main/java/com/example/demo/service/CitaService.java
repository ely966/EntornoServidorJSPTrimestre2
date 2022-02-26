package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cita;
import com.example.demo.model.CredencialesCita;
import com.example.demo.model.Mascota;
import com.example.demo.model.User;
import com.example.demo.repository.CitasRepository;

@Primary
@Service("CitaService")
public class CitaService {
	@Autowired CitasRepository citaRepo;
	 @Autowired private MascotaService mascotaServi;
	
	public Cita addCita(CredencialesCita cita,User usuario) {
		Cita nuevaCita = new Cita();
		nuevaCita.setCliente(usuario);
		nuevaCita.setFecha(cita.getFecha());
		nuevaCita.setHora(cita.getFecha());
		Mascota mascota= mascotaServi.encontrarId(cita.getPetid());
		nuevaCita.setPet(mascota);
		nuevaCita.setMotivo(cita.getMotivo());
		citaRepo.save(nuevaCita);
		return nuevaCita;
	}
	public Cita findCita(Long id){
		Cita cita = new Cita();
		List<Cita> citas=citaRepo.findAll();
		for(int i =0; i < citaRepo.count(); i=i+1) {
			if (citas.get(i).getId().equals(id)) {
				cita= citas.get(i);
			}
		}
		return null;
	}
	
	public List<Cita> mostrarCitasUser(User cliente){
		return cliente.getCitas();
		
	}
	/**
	 * Eliminar una cita
	 * @param usuario
	 * @param cita
	 * @return
	 */
	public Cita delete(User usuario,Cita cita) {
		/**Eliminamos su union con la mascota**/
		cita.setPet(null);
		//**Eliminamos su union con cliente**/
		cita.setCliente(null);
		//**Guardamos la cita sin uniones**/
		citaRepo.save(cita);
		citaRepo.delete(cita);/**Eliminamos la cita**/
		return cita;
	}
	
	public void deleteByPet(Long id) {
		List<Cita>citas=citaRepo.findAll();
		for (int i=0; citaRepo.count() > i;i=i+1) {
			if(citas.get(i).getPet().getId() == id) {
				citaRepo.delete(citas.get(i));
			}
		}
	}

}

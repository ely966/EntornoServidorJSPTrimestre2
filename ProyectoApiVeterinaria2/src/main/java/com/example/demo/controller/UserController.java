package com.example.demo.controller;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Mascota;
import com.example.demo.model.User;
import com.example.demo.repository.MascotaRepository;
import com.example.demo.repository.UserRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired private UserRepo userRepo;
    @Autowired private MascotaRepository mascotaRepo;
    /**
     * Recoge el token y con este recoge la informacion
     * @return
     */
    @GetMapping("/user")
    public User getUserDetails(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email).get();
    }
    /**
     * Con este metodo añadiremos mascota nueva
     * @param Trae la informacion de la mascota. Comprueba que el usuario
     */
    
    @PostMapping("/cliente/mascota")
    public void addPet(@RequestBody Mascota mascota){
    	//try {
    		//UsernamePasswordAuthenticationToken authInputToken =
                  // new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword())
            //authManager.authenticate(authInputToken);

       try {
    	String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User usuario = userRepo.findByEmail(email).get();
       
        if (usuario !=null) {
        	 Mascota nuevaMascota = new Mascota();
        	 nuevaMascota.setNombre(mascota.getNombre());
        	 nuevaMascota.setEdad(mascota.getEdad());
        	 nuevaMascota.setRaza(mascota.getRaza());
        	 nuevaMascota.setTipo(mascota.getTipo());
        	 nuevaMascota.setUsuario(usuario);
        	 mascotaRepo.save(nuevaMascota);
        	//return usuario;
        }
       }catch (AuthenticationException authExc){
           throw new RuntimeException("Incorrecto Crear mascota");
       }


       // return userRepo.findByEmail(email).get();//.get
    }
    
    /**
     * Con este metodo añadiremos cita nueva
     * @param Trae la informacion de la cita. COmprueba que el usuario y la mascota existe
     * @return
     * dato extra: falta añadir veterinario
     */
    @PostMapping("/cliente/cita")
    public User addCita(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email).get();
    }
    
   


}
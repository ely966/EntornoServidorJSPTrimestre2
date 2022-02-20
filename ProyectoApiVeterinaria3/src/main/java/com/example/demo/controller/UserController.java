package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;


@RestController
public class UserController {

    @Autowired private UserRepo userRepo;

    /**
     * 
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
     * @return
     * dato extra: falta añadir veterinario
     */
    
    @PostMapping("/cliente/mascota")
    public User addPet(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email).get();//.get
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
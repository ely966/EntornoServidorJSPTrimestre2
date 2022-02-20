/**package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;
@ComponentScan({"com.delivery.request"})
@Primary
@Service("UsuarioServiceBD")
public class UserServiceBD implements UserService{

	@Autowired
	private UserRepo repositorioUser;
	
	@Autowired
	public List<User> findAll(){
		return repositorioUser.findAll();
	}
	@Autowired
	public User findById (Long id) {
		return repositorioUser.getById(id);
	}
	@Autowired
	public User add (User user) {
		return repositorioUser.save(user);
	}
	@Autowired
	public User edit (User user, Long id) {
		if(repositorioUser.existsById(id)) {
			user.setId(id);
			return repositorioUser.save(user);
		}else {
			return null;
		}
	}
	@Autowired
	public User delete (Long id) {
		if(repositorioUser.existsById(id)) {
			User user = repositorioUser.findById(id).get();
			repositorioUser.deleteById(id);
			return user;
		}else {
			return null;
		}
	}
	
	@Autowired
	public Boolean findByEmail(List<User> users, String emailNuevoCorreo){
		Boolean encontrado =false;
		int i=0;
		while ( i<users.size() && !encontrado) {
			if(users.get(i).getEmail().equals(emailNuevoCorreo)) {
				//Si el correo es encontrado. No puede repetirse el correo
				return true;
			}
			else {
				i=i+1;
			}
			}
		return false;
	}
}**/

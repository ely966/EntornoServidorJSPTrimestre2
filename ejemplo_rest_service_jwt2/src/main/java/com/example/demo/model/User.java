package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
//Anotaciones lombok para incluir código de getters, setters, toString y constructor sin argumentos de manera rápida
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//private String userName;
	private String nombre;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) /**Que la pass no se incluya en el json respuesta**/
	private String password;
	//@OneToMany(mappedBy="usuario")
	//@JsonManagedReference("userMascota") 
	//private List<Mascota> mascotas;
	//private String rol = "USER";
	
	
	public User(String nombre, String email, String password) {
		super();
		//this.userName = userName;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		
	}
}
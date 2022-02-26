package com.example.demo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cita {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference("userCita")  
	@JoinColumn(name="Cliente")
	private User cliente;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="pet")
	private Mascota pet;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Temporal(TemporalType.TIME)
	private Date hora;
	private String motivo;
	
	
	public Cita(Mascota pet, Date fecha, Date hora, String motivo) {
		super();
		this.pet = pet;
		this.fecha = fecha;
		this.hora = hora;
		this.motivo = motivo;
	}


	public Cita(Mascota pet, Date fecha, String motivo) {
		super();
		this.pet = pet;
		this.fecha = fecha;
		this.motivo = motivo;
	}
	
	
	
	
}

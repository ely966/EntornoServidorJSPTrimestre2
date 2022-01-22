package clases;

import java.util.ArrayList;

public class Garaje {
	private String nombre;
	protected ArrayList<Automovil> Garaje;
	
	public Garaje() {
		super();
		this.nombre = nombre;
		Garaje = new ArrayList<Automovil>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Automovil> getGaraje() {
		return Garaje;
	}

	public void setGaraje(ArrayList<Automovil> garaje) {
		Garaje = garaje;
	}

	@Override
	public String toString() {
		return "Garaje [nombre=" + nombre + ", Garaje=" + Garaje + "]";
	}
	
	
}

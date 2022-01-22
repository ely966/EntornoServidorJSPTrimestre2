package clases;

public class Automovil {
	private int id;
	private int idSig=id;
	private String marca;
	private int puertas;
	private int precio;
	private int preciototal;
	private Boolean tieneExtras=false;
	private int numExtras;
	
	public Automovil() {
		super();
		id=idSig;
		idSig=idSig+1;
		this.marca = marca;
		this.puertas = puertas;
		this.precio = precio;
		this.preciototal = precio;
		this.numExtras=numExtras;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getPuertas() {
		return puertas;
	}
	public void setPuertas(int puertas) {
		//if (puertas < 0) {
			
		//}
		//else {
			this.puertas = puertas;
			//}
	}
	public int getPrecio() {
		return precio;
		}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getPreciototal() {
		return preciototal;
	}
	public void setPreciototal(int preciototal, boolean extr) {
		if(extr) {
			tieneExtras=true;
			numExtras=numExtras+1;
		}
		this.preciototal = preciototal;
	}
	@Override
	public String toString() {
		return "Automovil [marca=" + marca + ", puertas=" + puertas + ", precio=" + precio + ", preciototal="
				+ preciototal + "]";
	}
	
	public String toStringMod() {
		return "El automóvil introducido es de marca : " + marca + " , y tiene " + puertas + " puertas. Su precio inicial sería " + precio + " euros ,pero como tiene "+ numExtras +" extras, su precio total es "
				+ preciototal + " euros .";
	}
	
}

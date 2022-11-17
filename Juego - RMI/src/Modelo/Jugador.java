package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements Ijugador, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private ArrayList<Carta>cartas;
	
	public Jugador(String id) {
		this.id=id;
		this.cartas=new ArrayList<Carta>();
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	@Override
	public List<Carta> getCartas() {
		return this.cartas;
	}
	
	public void agregarCarta(Carta carta) {
		this.cartas.add(carta);
	}
	

	public void tirarCarta(Carta carta) {
		cartas.remove(carta);
	}

	public int cantidadDeCartas() {
		return cartas.size();
	}
}

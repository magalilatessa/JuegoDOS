package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Mazo implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Carta>cartas;
	
	public Mazo() {
		this.cartas=new ArrayList<Carta>();
	}
	public void cargarCartas() {
		Carta carta = null;
		for(Color color: Color.values()){
			if (color!=Color.MULTICOLOR && color!=Color.NULL){//para todos los colores menos multicolor
				for(int i = 1;i<=10;i++){
					if (i!=2) {
					for (int x=1;x<=2;x++) {
						carta=new Carta(String.valueOf(i),color);
						cartas.add(carta);//agregar dos de esa carta
						}
					if(i>=5) {
						carta=new Carta(String.valueOf(i),color);
						cartas.add(carta);//si el numero es menor a 6 agregar una mas de esa carta
					}
				}}
			}
		}
		for(Color color: Color.values()){
  				if (color!=Color.MULTICOLOR && color!=Color.NULL){//para todos los colores menos multicolor
					for (int x=1;x<=2;x++) {
						carta=new Carta(("#"),color);
						cartas.add(carta);//agregar dos de esa carta
						}
				
			}
		}
		
		for (int x=1;x<=12;x++) {//agregar 12 2 multicolor
			carta=new Carta("2",Color.MULTICOLOR);
			cartas.add(carta);
		}
	}

	public Carta sacarCarta() {
		if (this.cartas.size()!=0) {
			int posCarta = (int)(Math.random()*this.cartas.size());
			return cartas.remove(posCarta); //saca d atras para delante
		}else {
		Carta carta =new Carta("0",Color.NULL);
		return carta;}
	}
	public void agregarCarta(Carta carta) {
		 this.cartas.add(carta);
	}
	public void MazoDescarteAjuego(Mazo mazoDescarte) {
		Carta carta=new Carta("0",Color.MULTICOLOR);
		while (mazoDescarte.cartas.size()!=0) {
			this.cartas.add(mazoDescarte.sacarCarta());
		}
	}
}


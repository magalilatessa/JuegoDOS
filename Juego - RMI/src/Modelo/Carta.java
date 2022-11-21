package Modelo;

import java.io.Serializable;

public class Carta implements Serializable{
	private static final long serialVersionUID = 1L;
	private String numero;
	private Color color; 
	
	public Carta(String numero, Color color){
		this.numero=numero;
		this.color=color;
	}
	
	public String getNumero() {
		return numero;
	}
	public Color getColor() {
		return color;
	}
	
	
	//Comparacion de carta con la que esta en el mazo(this) y la que pone el jugador
	public CompararCarta comparar(Carta cartaJugador) {
		boolean igualNumero=false;
		if ((cartaJugador.numero.equals(this.numero))) {
			igualNumero=true;
		}else if (cartaJugador.numero.equals("#")||(this.numero.equals("#"))) {
			igualNumero=true;
		}
		if (igualNumero){
			if((cartaJugador.color==this.color)|| (cartaJugador.color==Color.MULTICOLOR)) {
				return CompararCarta.IGUAL_NUMERO_COLOR;
			}else return CompararCarta.IGUAL_NUMERO;
		}
		return CompararCarta.DISTINTA;
	}
	
	public CompararCarta comparar(Carta cartaJugador1,Carta cartaJugador2) {
		boolean igualNumero=false;
		if (this.numero.equals("#")){
			igualNumero=true;
		}else if (cartaJugador1.numero.equals("#")&&
			(Integer.parseInt(cartaJugador2.numero)<Integer.parseInt(this.numero))){
			igualNumero=true;
		}else if (cartaJugador2.numero.equals("#")&&
				(Integer.parseInt(cartaJugador1.numero)<Integer.parseInt(this.numero))) {
				igualNumero=true;
		}else if ((Integer.parseInt(cartaJugador1.numero)+
				Integer.parseInt(cartaJugador2.numero))==Integer.parseInt(this.numero)){
			igualNumero=true;
		}
		if (igualNumero) {
			if (((cartaJugador1.color==(this.color))&&(cartaJugador2.color==(this.color)))){
				return CompararCarta.IGUAL_NUMERO_COLOR;
			}else if ((cartaJugador1.color==(Color.MULTICOLOR))&&(cartaJugador2.color==(this.color))) {
				return CompararCarta.IGUAL_NUMERO_COLOR;
			}else if ((cartaJugador2.color==(Color.MULTICOLOR))&&(cartaJugador1.color==(this.color))) {
				return CompararCarta.IGUAL_NUMERO_COLOR;
			}else if ((cartaJugador1.color==(Color.MULTICOLOR))&&(cartaJugador2.color==(Color.MULTICOLOR))){
				return CompararCarta.IGUAL_NUMERO_COLOR;
			}else return CompararCarta.IGUAL_NUMERO;
		}
		return CompararCarta.DISTINTA;
	}
	
	public String toString() {
		 String colorCarta = this.color.name();
		 String numeroCarta= this.numero;
		 return ("["+numeroCarta + ","+ colorCarta+"]");
	}

			
}

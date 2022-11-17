package Modelo;

public class Carta {
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
		if ((cartaJugador.numero.equals(this.numero)) 
		   || (cartaJugador.numero.equals("#") || this.numero.equals("#"))) {
			if (cartaJugador.color==this.color) {
				return CompararCarta.IGUAL_NUMERO_COLOR;
			}
			return CompararCarta.IGUAL_NUMERO;
		}
		return CompararCarta.DISTINTA;
	}
	public CompararCarta comparar(Carta cartaJugador1,Carta cartaJugador2) {
		if ((cartaJugador1.numero+cartaJugador2.numero==this.numero) 
				  || (cartaJugador1.numero.equals("#") || cartaJugador2.numero.equals("#"))
				  || (this.numero.equals("#"))){
			if ((cartaJugador1.color==this.color)&&(cartaJugador2.color==this.color)) {
				return CompararCarta.IGUAL_NUMERO_COLOR;
			}
			return CompararCarta.IGUAL_NUMERO;
		}
		return CompararCarta.DISTINTA;
	}
	
	public String toString() {
		 String colorCarta = this.color.name();
		 String numeroCarta= this.numero;
		 return ("["+numeroCarta + ","+ colorCarta+"]");
	}

			
}

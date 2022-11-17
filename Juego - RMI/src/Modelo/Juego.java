package Modelo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import Observer.Iobservable;
import Observer.Iobservador;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Juego extends ObservableRemoto implements Ijuego{

	private int posJugadorActual;
	private Mazo mazoDescarte;
	private Mazo mazoJuego;
	private ArrayList<Carta>cartasEnMesa;
	private ArrayList<Jugador>jugadores;
	private List<Iobservador> observadores;
	
	
	public Juego(){
		this.observadores = new ArrayList<>();
		this.cartasEnMesa=new ArrayList<>();
		this.jugadores = new ArrayList<>();
		this.posJugadorActual=0;
		this.mazoJuego=new Mazo();
		mazoJuego.cargarCartas(); //solo una vez      
		mazoDescarte=new Mazo();

	}
	
	@Override
	public void agregarJugador(String id) {
		//VALIDAR QUE SEA HASTA 6 JUGADORES
		Jugador jugador=new Jugador(id);
		jugadores.add(jugador);
		if (jugadores.size()==6) {
			try {
				this.notificar(EstadoJuego.NO_ENTRAN_MAS_JUGADORES);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (jugadores.size()>=2) {
			try {
				this.notificar(EstadoJuego.JUGADOR_AGREGADO_PUEDE_EMPEZAR);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			this.notificar(EstadoJuego.JUGADOR_AGREGADO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Jugador JugadorSiguiente() {
		if (this.posJugadorActual==this.jugadores.size()-1) { 
			this.posJugadorActual= 0;
			return jugadores.get(posJugadorActual);
		}
		this.posJugadorActual= this.posJugadorActual+1;
		return jugadores.get(posJugadorActual);
	}
	
	
	@Override
	public void agregadorObservador(Iobservador observador) {
		this.observadores.add(observador);	
	}

	@Override
	public ArrayList<Jugador> listarJugadores() {
		return jugadores;
	}
	@Override
	public int getJugadorActual() {
		return this.posJugadorActual;
	}
	
	@Override
	public void repartirCartas() { //reparte todas las cartas a los jugadores
		for (int i=1; i<=7;i++){
			for (Jugador jugador:jugadores){				
				Carta carta=this.mazoJuego.sacarCarta(); 
				jugador.agregarCarta(carta);
			}
		}
		//###################cast 1 carta x jugador###################
//		for (Jugador jugador:jugadores){
//			Carta carta=this.mazoJuego.sacarCarta(); 
//			jugador.agregarCarta(carta);
//   	}
		//y pone dos en mesa
		this.cartasEnMesa.add(this.mazoJuego.sacarCarta());
		this.cartasEnMesa.add(this.mazoJuego.sacarCarta());
		this.JugadorSiguiente();
		try {
			this.notificar(EstadoJuego.CARTAS_REPARTIDAS);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void pasarMazoDescarteAmazoJuego() {
		this.mazoJuego.MazoDescarteAjuego(mazoDescarte);
	}
	private void agregarCartaEnMesa() {
		if (this.mazoJuego.sacarCarta().getColor()==Color.NULL) {
			this.pasarMazoDescarteAmazoJuego();
		}
		this.cartasEnMesa.add(this.mazoJuego.sacarCarta());
	}
	@Override
	public void agregarCartaEnMesa(Carta cartaJugador) {
		this.cartasEnMesa.add(cartaJugador);
	}
	@Override
	public ArrayList<Carta> getCartasEnMesa() {
		return this.cartasEnMesa;
	}

	@Override
	public void jugarActual(int posCartaJugada,int posCartaJugada2, int posCartaEnMesa) {
		Carta cartaJugador=this.jugadores.get(posJugadorActual).getCartas().get(posCartaJugada);
		Carta carta2Jugador=this.jugadores.get(posJugadorActual).getCartas().get(posCartaJugada2);
		Carta cartaMesa=this.cartasEnMesa.get(posCartaEnMesa);
		CompararCarta comparacion=cartaMesa.comparar(cartaJugador,carta2Jugador);
		
		switch(comparacion) {
		case IGUAL_NUMERO:
			this.jugadores.get(posJugadorActual).tirarCarta(cartaJugador);
			this.jugadores.get(posJugadorActual).tirarCarta(carta2Jugador);
			//las descarto
			this.mazoDescarte.agregarCarta(cartaJugador);
			this.mazoDescarte.agregarCarta(carta2Jugador);
			this.mazoDescarte.agregarCarta(cartaMesa); 
			this.cartasEnMesa.remove(posCartaEnMesa); //la saco de la lista
			this.agregarCartaEnMesa();

			//si el jugador no tiene mas cartas se termina el juego
			if ((this.jugadores.get(posJugadorActual).getCartas().size())==0) {
				try {
					this.notificar(EstadoJuego.JUEGO_TERMINADO);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			
			this.JugadorSiguiente();
			try {
				this.notificar(EstadoJuego.JUGAR_SIGUIENTE);
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case IGUAL_NUMERO_COLOR:
			this.jugadores.get(posJugadorActual).tirarCarta(cartaJugador);
			this.jugadores.get(posJugadorActual).tirarCarta(carta2Jugador);
			//las descarto
			this.mazoDescarte.agregarCarta(cartaJugador);
			this.mazoDescarte.agregarCarta(carta2Jugador);
			this.mazoDescarte.agregarCarta(cartaMesa); 
			this.cartasEnMesa.remove(posCartaEnMesa); //la saco de la lista
			
			//A todos los jugades les sumo 1 carta
			for (int i=0;i<=this.jugadores.size();i++) {
				if (!jugadores.get(i).getId().equals(jugadores.get(this.posJugadorActual).getId())) {
					Carta carta=this.mazoJuego.sacarCarta();
					jugadores.get(i).agregarCarta(carta);
				}
			}
			
			//si el jugador no tiene mas cartas se termina el juego
			if ((this.jugadores.get(posJugadorActual).getCartas().size())==0) { 
				try {
					this.notificar(EstadoJuego.JUEGO_TERMINADO);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			

			try {
				this.notificar(EstadoJuego.CARTAS_TIRADAS_JUGADOR_PONER_CARTA);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case DISTINTA:
			try {
				this.notificar(EstadoJuego.CARTA_TIRADA_DISTINTA);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
	}
	

	@Override
	public void jugarActual(int posCartaJugada, int posCartaEnMesa) {
		Carta cartaJugador=this.jugadores.get(posJugadorActual).getCartas().get(posCartaJugada);
		Carta cartaMesa=this.cartasEnMesa.get(posCartaEnMesa);
		CompararCarta comparacion=cartaMesa.comparar(cartaJugador);
		
		switch(comparacion) {
		case IGUAL_NUMERO:
			this.jugadores.get(posJugadorActual).tirarCarta(cartaJugador);
			//las descarto
			this.mazoDescarte.agregarCarta(cartaJugador);
			this.mazoDescarte.agregarCarta(cartaMesa); 
			this.cartasEnMesa.remove(posCartaEnMesa); //la saco de la lista
			this.agregarCartaEnMesa();
			//si el jugador no tiene mas cartas se termina el juego
			if ((this.jugadores.get(posJugadorActual).getCartas().size())==0) {
				try {
					this.notificar(EstadoJuego.JUEGO_TERMINADO);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			
			this.JugadorSiguiente();
			try {
				this.notificar(EstadoJuego.JUGAR_SIGUIENTE);
			} catch (RemoteException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			break;
		case IGUAL_NUMERO_COLOR:
			this.jugadores.get(posJugadorActual).tirarCarta(cartaJugador);
			//las descarto
			this.mazoDescarte.agregarCarta(cartaJugador);
			this.mazoDescarte.agregarCarta(cartaMesa); 
			this.cartasEnMesa.remove(posCartaEnMesa); //la saco de la lista
			
			
			//si el jugador no tiene mas cartas se termina el juego
			if ((this.jugadores.get(posJugadorActual).getCartas().size())==0) { 
				try {
					this.notificar(EstadoJuego.JUEGO_TERMINADO);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			
			try {
				this.notificar(EstadoJuego.CARTAS_TIRADAS_JUGADOR_PONER_CARTA);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case DISTINTA:
			try {
				this.notificar(EstadoJuego.CARTA_TIRADA_DISTINTA);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}	
	}

	@Override
	public void robarCartaJugadorActual() {
		this.jugadores.get(posJugadorActual).agregarCarta(this.mazoJuego.sacarCarta());
		this.JugadorSiguiente();
		try {
			this.notificar(EstadoJuego.JUGAR_SIGUIENTE);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void ponerEnMesaCartaJugadorActual(int posCartaTirada) {
		Carta cartaJugador=this.jugadores.get(posJugadorActual).getCartas().get(posCartaTirada);
		this.jugadores.get(posJugadorActual).tirarCarta(cartaJugador);
		this.cartasEnMesa.add(cartaJugador);
		this.JugadorSiguiente();
	}

	@Override
	public int cantidadCartasJugadorActual() {
		return this.jugadores.get(posJugadorActual).cantidadDeCartas();
		
	}

	@Override
	public int cantidadCartasEnMesa() {
		return this.cartasEnMesa.size();
	}
	
	
	//###############REINICIAR##############
	@Override
	public void reiniciar() {
		this.observadores = new ArrayList<>();
		this.cartasEnMesa=new ArrayList<>();
		this.jugadores = new ArrayList<>();
		this.posJugadorActual=0;
		this.mazoJuego=new Mazo();
		mazoJuego.cargarCartas(); //solo una vez      
		mazoDescarte=new Mazo();
	}




	@Override
	public void notificar(Object evento) throws RemoteException {
		this.notificarObservadores(evento);
		
	}




}

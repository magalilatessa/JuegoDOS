package Controlador;

import java.util.ArrayList;

import Modelo.EstadoJuego;
import Modelo.Ijugador;
import Modelo.Juego;
import Modelo.Jugador;
import Observer.Iobservable;
import Observer.Iobservador;
import Vista.Ivista;
import Vista.VistaConsola;



public class Controlador implements Iobservador{
	private Juego modelo;
	private VistaConsola vista;
	
	public Controlador(Juego modelo, VistaConsola vista) {
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
		this.modelo.agregadorObservador(this);
	}

	@Override
	public void actualizar(Object estado, Iobservable observado) {
		if (estado instanceof EstadoJuego) {
			switch((EstadoJuego) estado) {
			case JUGADOR_AGREGADO:
				this.verJugadores();
				break;
			case CARTAS_REPARTIDAS:
				this.verCartasEnMesa();
				this.verCartasJugadorActual();
				this.vista.iniciarJuegoActual();
				break;
		//	case CARTA_TIRADA_DESCATADA_PUESTA_OTRA_CARTA:
		//	     this.verCartasEnMesa();
		//       this.verCartasQueLeQuedaron();
		//	     this.modelo.jugarSiguiente();
	    //       break;
		//	case CARTA_ROBADA:
		//		this.verCartasQueLeQuedaron();
		//		break;
			case JUGADOR_AGREGADO_PUEDE_EMPEZAR:
				this.verJugadores();
				this.vista.empezar();
				break;
			case JUEGO_TERMINADO:
				this.vista.terminar();
				break;
			case JUGAR_SIGUIENTE:
				this.verCartasEnMesa();
				this.verCartasJugadorActual();
				this.vista.iniciarJuegoActual();
				break;
			case CARTA_TIRADA_DISTINTA:
				this.vista.cartaDistinta();
				break;
			case NO_ENTRAN_MAS_JUGADORES:
				this.vista.noEntranMasJugadores();
			case CARTAS_TIRADAS_JUGADOR_PONER_CARTA:
				this.vista.ElegirCartaJugadorActual();
				this.verCartasEnMesa();
				this.verCartasJugadorActual();
				this.vista.iniciarJuegoActual();
				break;
			}	
		}
	}

	private ArrayList<Ijugador> castJugador(ArrayList<Jugador> jugadores){
		ArrayList<Ijugador>jugadoresI=new ArrayList<>();
		for (Jugador j:jugadores){
			jugadoresI.add(j);
		}
		return jugadoresI;
	}
	
	public void verCartasQueLeQuedaron() {
		ArrayList<Jugador> jugadores=this.modelo.listarJugadores();
		Ijugador jugadorActual=jugadores.get(this.modelo.getJugadorActual());
		this.vista.mostrarCartasDespuesDeJugar(jugadorActual);
		
	}
	
	public void verCartasEnMesa() {
		this.vista.mostrarCartasEnMesa(this.modelo.getCartasEnMesa());	
	}

	public void verCartasJugadorActual() {
		ArrayList<Jugador> jugadores=this.modelo.listarJugadores();
		Ijugador jugadorActual=jugadores.get(this.modelo.getJugadorActual());
		this.vista.mostrarCartaJugadorActual(jugadorActual);
	}
	
	public void verJugadores(){
		ArrayList<Jugador> jugadores=this.modelo.listarJugadores();
		this.vista.mostrarJugadores(this.castJugador(jugadores));
	}
	
	public void agregarJugador(String id) {
		this.modelo.agregarJugador(id);
	}
	public void iniciarJuego() {
		this.modelo.repartirCartas();
	}

	public void jugarSimple(String posCartaJugada, String posCartaEnMesa) {
		this.modelo.jugarActual(Integer.parseInt(posCartaJugada)-1,Integer.parseInt(posCartaEnMesa)-1);
		
	}
	public void jugarDoble(String posCartaJugada,String posCartaJugada1, String posCartaEnMesa) {
		this.modelo.jugarActual(Integer.parseInt(posCartaJugada)-1,Integer.parseInt(posCartaJugada1)-1,Integer.parseInt(posCartaEnMesa)-1);
		
	}

	public void robarCartaJugadorActual() {
		this.modelo.robarCartaJugadorActual();
		
	}

	public Ijugador obtenerGanador() {
		Ijugador ganador=this.modelo.listarJugadores().get(this.modelo.getJugadorActual());
		return ganador;
	}
	
	public Ijugador obtenerJugadorActual() {
		Ijugador ganador=this.modelo.listarJugadores().get(this.modelo.getJugadorActual());
		return ganador;
	}

	public void ponerEnMesaCartaJugador(String posCartaTirada) {
		this.modelo.ponerEnMesaCartaJugadorActual(Integer.parseInt(posCartaTirada)-1);
		
	}
	
	public int obtenerCantidadCartasJugadorActual() {
		return this.modelo.cantidadCartasJugadorActual();
	}

	public int obtenerCantidadCartasEnMesa() {
		return this.modelo.cantidadCartasEnMesa();
	}

	public void reiniciar() {
		this.modelo.reiniciar();
		
	}
}


package Controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;

import Modelo.EstadoJuego;
import Modelo.Ijuego;
import Modelo.Ijugador;
import Modelo.Juego;
import Modelo.Jugador;
import Observer.Iobservable;
import Observer.Iobservador;
import Vista.Ivista;
import Vista.VistaConsola;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;



public class Controlador implements IControladorRemoto{
	private Ijuego modelo;
	private VistaConsola vista;
	
	public Controlador(VistaConsola vista) {
		this.vista = vista;
		this.vista.setControlador(this);
	}

	@Override
	public void actualizar(IObservableRemoto observable, Object estado) throws RemoteException {
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
	
	public void verCartasQueLeQuedaron() throws RemoteException {
		ArrayList<Jugador> jugadores=this.modelo.listarJugadores();
		Ijugador jugadorActual=jugadores.get(this.modelo.getJugadorActual());
		this.vista.mostrarCartasDespuesDeJugar(jugadorActual);
		
	}
	
	public void verCartasEnMesa() throws RemoteException {
		this.vista.mostrarCartasEnMesa(this.modelo.getCartasEnMesa());	
	}

	public void verCartasJugadorActual() throws RemoteException {
		ArrayList<Jugador> jugadores=this.modelo.listarJugadores();
		Ijugador jugadorActual=jugadores.get(this.modelo.getJugadorActual());
		this.vista.mostrarCartaJugadorActual(jugadorActual);
	}
	
	public void verJugadores() throws RemoteException{
		ArrayList<Jugador> jugadores=this.modelo.listarJugadores();
		this.vista.mostrarJugadores(this.castJugador(jugadores));
	}
	
	public void agregarJugador(String id) throws RemoteException {
		this.modelo.agregarJugador(id);
	}
	public void iniciarJuego() throws RemoteException {
		this.modelo.repartirCartas();
	}

	public void jugarSimple(String posCartaJugada, String posCartaEnMesa) throws NumberFormatException, RemoteException {
		this.modelo.jugarActual(Integer.parseInt(posCartaJugada)-1,Integer.parseInt(posCartaEnMesa)-1);
		
	}
	public void jugarDoble(String posCartaJugada,String posCartaJugada1, String posCartaEnMesa) throws NumberFormatException, RemoteException {
		this.modelo.jugarActual(Integer.parseInt(posCartaJugada)-1,Integer.parseInt(posCartaJugada1)-1,Integer.parseInt(posCartaEnMesa)-1);
		
	}

	public void robarCartaJugadorActual() throws RemoteException {
		this.modelo.robarCartaJugadorActual();
		
	}

	public Ijugador obtenerGanador() throws RemoteException {
		Ijugador ganador=this.modelo.listarJugadores().get(this.modelo.getJugadorActual());
		return ganador;
	}
	
	public Ijugador obtenerJugadorActual() throws RemoteException {
		Ijugador ganador=this.modelo.listarJugadores().get(this.modelo.getJugadorActual());
		return ganador;
	}

	public void ponerEnMesaCartaJugador(String posCartaTirada) throws NumberFormatException, RemoteException {
		this.modelo.ponerEnMesaCartaJugadorActual(Integer.parseInt(posCartaTirada)-1);
		
	}
	
	public int obtenerCantidadCartasJugadorActual() throws RemoteException {
		return this.modelo.cantidadCartasJugadorActual();
	}

	public int obtenerCantidadCartasEnMesa() throws RemoteException {
		return this.modelo.cantidadCartasEnMesa();
	}

//	public void reiniciar() {
//		this.modelo.reiniciar();
//		
//	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T modelo) throws RemoteException {
		this.modelo = (Ijuego) modelo;
		
	}


}


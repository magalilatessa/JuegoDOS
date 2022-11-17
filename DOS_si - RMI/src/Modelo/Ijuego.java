package Modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Observer.Iobservador;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface Ijuego extends IObservableRemoto{

	void agregarJugador(String id);

	Jugador JugadorSiguiente();


	void agregadorObservador(Iobservador observador);

	ArrayList<Jugador> listarJugadores();

	int getJugadorActual();

	void repartirCartas();

	void agregarCartaEnMesa(Carta cartaJugador);

	ArrayList<Carta> getCartasEnMesa();

	void jugarActual(int posCartaJugada, int posCartaJugada2, int posCartaEnMesa);

	void jugarActual(int posCartaJugada, int posCartaEnMesa);

	void robarCartaJugadorActual();

	void ponerEnMesaCartaJugadorActual(int posCartaTirada);

	int cantidadCartasJugadorActual();

	int cantidadCartasEnMesa();

	//###############REINICIAR##############
	void reiniciar();

	void notificar(Object evento) throws RemoteException;

}
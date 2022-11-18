package Modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import Observer.Iobservador;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface Ijuego extends IObservableRemoto{

	void agregarJugador(String id) throws RemoteException;

	Jugador JugadorSiguiente() throws RemoteException;


	ArrayList<Jugador> listarJugadores()throws RemoteException;

	int getJugadorActual()throws RemoteException;

	void repartirCartas() throws RemoteException;

	void agregarCartaEnMesa(Carta cartaJugador)throws RemoteException;

	ArrayList<Carta> getCartasEnMesa()throws RemoteException;

	void jugarActual(int posCartaJugada, int posCartaJugada2, int posCartaEnMesa) throws RemoteException;

	void jugarActual(int posCartaJugada, int posCartaEnMesa) throws RemoteException;

	void robarCartaJugadorActual() throws RemoteException;

	void ponerEnMesaCartaJugadorActual(int posCartaTirada)throws RemoteException;

	int cantidadCartasJugadorActual()throws RemoteException;

	int cantidadCartasEnMesa()throws RemoteException;

	//###############REINICIAR##############
	//void reiniciar();

	void notificar(Object evento) throws RemoteException;

}
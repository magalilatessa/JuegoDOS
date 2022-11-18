package Vista;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controlador.Controlador;
import Modelo.Carta;
import Modelo.Ijugador;


public class VistaConsola implements Ivista{

	private Scanner entrada;
	private Controlador controlador;
	
	public VistaConsola() {
		this.entrada = new Scanner(System.in);
	}
	
	@Override
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
		
	}
	public void mostrarEncabezado() {
		System.out.println("#########################");
		System.out.println("####### JUEGO DOS #######");
		System.out.println("#########################");
		System.out.println();
	}
	
	public void mostrarMenuPrincipal() {
		System.out.println("#########################");
		System.out.println("Selecciona una opcion:");
		System.out.println("1 - Agregar jugador");
		System.out.println("2 - Ver jugadores");
		System.out.println("3 - Mostrar reglas");
		System.out.println();
		System.out.println("99 - Salir");
	}
	
	@Override
	public void iniciar() throws RemoteException {
		boolean salir = false;
		this.mostrarEncabezado();
		while(!salir ) {
			this.mostrarMenuPrincipal();
			String opcion = this.entrada.nextLine();
			switch (opcion) {
				case "1":
					System.out.println("Ingrese el nombre del nuevo jugador");
					String nombre = this.entrada.nextLine();
					this.controlador.agregarJugador(nombre);
//					this.agregarJugadorCast();
					break;
				case "2":
					this.controlador.verJugadores();
					break;
				case "3":
					this.mostrarReglas();
					break;
				case "99":
					salir = true;
					System.out.println("Recuerde volver!");
					break;
				default:
					System.out.println("Opcion no valida.");
			}
		}
	}
	public void noEntranMasJugadores() {
		System.out.println("########################");
		System.out.println("####### JUEGO DOS #######");
		System.out.println("########################");
		System.out.println();
		System.out.println("Selecciona una opcion:");
		System.out.println("1 - Ver jugadores");
		System.out.println("2 - Mostrar reglas");
		System.out.println("3 - Empezar");
		System.out.println();
		System.out.println("99 - Salir");
		
	}
	public void menuNoEntranMasJugadores() throws RemoteException {
		boolean salir=true;
		do {
			this.mostrarMenuEmpezar();
			String opcion = this.entrada.nextLine();
		switch (opcion) {
				case "1":
					this.controlador.verJugadores();
					break;
				case "3":
					this.controlador.iniciarJuego();
					break;
				case "2":
					this.mostrarReglas();
					break;
				case "99":
					salir=true;
					System.out.println("Recuerde volver!");
					break;
				default:
					System.out.println("Opcion no valida.");
					salir=false;
			}
		}while (!salir);
	}
	
	
	public void mostrarMenuEmpezar() {
		System.out.println("########################");
		System.out.println("####### JUEGO DOS #######");
		System.out.println("########################");
		System.out.println();
		System.out.println("Selecciona una opcion:");
		System.out.println("1 - Agregar jugador");
		System.out.println("2 - Ver jugadores");
		System.out.println("3 - Mostrar reglas");
		System.out.println("4 - Empezar");
		System.out.println();
		System.out.println("99 - Salir");
		
	}
	public void empezar() throws RemoteException {
		boolean salir=true;
		do {
			this.mostrarMenuEmpezar();
			String opcion = this.entrada.nextLine();
		switch (opcion) {
				case "1":
					System.out.println("Ingrese el nombre del nuevo jugador");
					String nombre = this.entrada.nextLine();
					this.controlador.agregarJugador(nombre);
					break;
				case "2":
					this.controlador.verJugadores();
					break;
				case "4":
					this.controlador.iniciarJuego();
					break;
				case "3":
					this.mostrarReglas();
					break;
				case "99":
					salir=true;
					System.out.println("Recuerde volver!");
					break;
				default:
					System.out.println("Opcion no valida.");
					salir=false;
			}
		}while (!salir);
	}
	
	
	public void mostrarMenuIniciar() {
		System.out.println();
		System.out.println("Selecciona una opcion:");
		System.out.println("1 - Jugar simple");
		System.out.println("2 - Jugar doble");
		System.out.println("99 - Robar carta");
	}
	
	public void iniciarJuegoActual() throws NumberFormatException, RemoteException {
		this.mostrarMenuIniciar();
		String opcion = this.entrada.nextLine();
		int cantidadCartasJugadorActual=this.controlador.obtenerCantidadCartasJugadorActual();
		int cantidadCartasEnMesa=this.controlador.obtenerCantidadCartasEnMesa();
		switch (opcion) {
			case "1":
				System.out.println("Ingrese la posicion de la carta que quiere jugar");
				String posCartaJugada = this.entrada.nextLine();
				while (cantidadCartasJugadorActual<(Integer.parseInt(posCartaJugada))) {
					System.out.println("****La posicion de la carta no es correcta****");
					System.out.println("Vuelva a ingresar la posicion de la carta que quiere jugar");
					posCartaJugada = this.entrada.nextLine();
				}
				System.out.println("Ingrese la posicion de la carta en mesa con la que quiere jugar");
				String posCartaEnMesa = this.entrada.nextLine();
				while (cantidadCartasEnMesa<(Integer.parseInt(posCartaEnMesa))) {
					System.out.println("****La posicion de la carta no es correcta****");
					System.out.println("Vuelva a ingresar la posicion de la carta en la mesa con la que quiere jugar");
					posCartaEnMesa = this.entrada.nextLine();
				}
				this.controlador.jugarSimple(posCartaJugada,posCartaEnMesa);
				
				break;
			case "2":
				System.out.println("Ingrese la posicion de la  primera carta que quiere jugar");
				String posCartaJugada1 = this.entrada.nextLine();
				System.out.println("Ingrese la posicion de la  segunda carta que quiere jugar");
				String posCartaJugada2 = this.entrada.nextLine();
				System.out.println("Ingrese la posicion de la carta en mesa con la que quiere jugar");
				String posCartaEnMesa1 = this.entrada.nextLine();
				this.controlador.jugarDoble(posCartaJugada1,posCartaJugada2,posCartaEnMesa1);
				break;
			case "99":
				this.controlador.robarCartaJugadorActual();
				break;
			default:
				System.out.println("Opcion no valida.");
		}
}
	
	

	public void mostrarJugadores(List<Ijugador> jugadores) {
		System.out.println("Los jugadores actuales son: ");
		for (Ijugador jugador:jugadores) {
			System.out.println(jugador.getId());
		}
	}

	public void mostrarCartaJugadorActual(Ijugador jugadorActual) {
		System.out.println();
		System.out.println("Es el turno de: "+ jugadorActual.getId());
		System.out.println("Sus cartas son: ");
		int i=0;
		for (Carta carta: jugadorActual.getCartas()) {
			i++;
			System.out.println(i+".    "+carta.toString()+",");
		}
		
	}

	public void mostrarCartasEnMesa(ArrayList<Carta> cartasEnMesa) {
		System.out.println("Las cartas en mesa son:");
		int i =0;
		for (Carta carta: cartasEnMesa) {
			i++;
			System.out.println(i+".    "+carta.toString()+",");
		}
		
	}

	


	public void mostrarCartasDespuesDeJugar(Ijugador jugadorActual) {
		System.out.println("Sus cartas son: ");
		int i=0;
		for (Carta carta: jugadorActual.getCartas()) {
			i++;
			System.out.println(i+".    "+carta.toString()+",");
		}
		
	}




	
	
//	public void agregarJugadorCast() {
//		this.controlador.agregarJugador("Lucas");
//		this.controlador.agregarJugador("Magali");
//	}

	public void cartaDistinta() throws NumberFormatException, RemoteException {
		System.out.println("Las cartas son de distinto número");
		this.iniciarJuegoActual();
		
	}

	public void ElegirCartaJugadorActual() throws NumberFormatException, RemoteException {
		System.out.println("Debe elegir una carta para poner en la mesa");
		this.mostrarCartasDespuesDeJugar(this.controlador.obtenerJugadorActual());
		System.out.println("Ingrese la posicion de la carta");
		String posCartaTirada = this.entrada.nextLine();
		this.controlador.ponerEnMesaCartaJugador(posCartaTirada);
	}
	

	
	public void mostrarReglas() {
		System.out.println("####REGLAS####");
		System.out.println("##JUEGO DOS##");
		System.out.println();
		System.out.println("Se admiten de 2 a 6 jugadores");
		System.out.println("OBJETIVO: se el primero en quedarse sin cartas");
		System.out.println();
		System.out.println("Cada jugador, en su turno intentará hacer juego con una o dos cartas de la mesa ");
		System.out.println("utilizando las cartas que tiene en la mano, si no puede debera robar una del mazo");					
		System.out.println();
		System.out.println("Formas de hacer juego con las cartas de la mesa");
		System.out.println("####JUEGO SIMPLE####");
		System.out.println("Tirar una carta del MISMO NUMERO que alguna de la mesa");
		System.out.println("*Si la carta coincide tanbien el color, "
				+ "debera agregar una de sus cartas a la mesa");
		System.out.println();
		System.out.println("####JUEGO DOBLE####");
		System.out.println("Tirar dos cartas cuyos números sumen el de una de las cartas de la mesa");
		System.out.println("Si las dos cartas utilizadas coinciden con el color de la carta de la mesa");
		System.out.println("debera tirar una carta a la mesa ");  
		System.out.println("y el resto de los jugadores robara una carta del mazo");
		System.out.println();
		System.out.println("Si un jugador no puede formar un juego, debera ROBAR UNA CARTA del mazo");
		System.out.println();
		System.out.println("Luego terminar el turno, ");
		System.out.println("las cartas con las que hizo juego en mesa se descartaran y se reemplazaran");		
		System.out.println();
		System.out.println("##CARTAS ESPECIALES##");
		System.out.println("COMODIN DOS: cuenta como un 2 de cualquier color");
		System.out.println("COMODIN #: cuenta como cualquier numero del 1 al 10");
		System.out.println();
		System.out.println("##JUEGO TERMINADO##");
		System.out.println("El juego se termina cuando un jugador (el ganador) se queda sin cartas");
		
	}
	
	
	public void mostrarMenuTerminar() throws RemoteException {
		System.out.println("#####################################");
		System.out.println("####### EL JUEGO HA TERMINADO #######");
		System.out.println("#####################################");
		System.out.println();
		Ijugador ganador=this.controlador.obtenerGanador();
		System.out.println("El GANADOR fue: "+ ganador.getId());
		System.out.println();
		System.out.println("Selecciona una opcion:");
		System.out.println("1 - Jugar otra vez");
		System.out.println("2 - Salir");
		System.out.println();
	}
	public void terminar() throws RemoteException {
		boolean salir = false;
		while(!salir ) {
			this.mostrarMenuTerminar();
			String opcion = this.entrada.nextLine();
			switch (opcion) {
				case "1":
					//this.controlador.reiniciar();
					break;
				case "2":
					System.exit(0);
					break;
				default:
					System.out.println("Opcion no valida.");
			}
		}
	}


}



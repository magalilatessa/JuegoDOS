package Modelo;

public enum EstadoJuego {
	JUGADOR_AGREGADO, 
	JUGADOR_AGREGADO_PUEDE_EMPEZAR,
	JUGAR_SIGUIENTE,
	CARTAS_REPARTIDAS,
	CARTA_ROBADA,
	CARTA_TIRADA_DESCATADA_PUESTA_OTRA_CARTA, 
	CARTAS_TIRADAS_JUGADOR_PONER_CARTA,
	JUEGO_TERMINADO, 
	CARTA_TIRADA_DISTINTA, 
	CARTAS_TIRADAS_JUGADOR_PONER_CARTA_SUMAR_A_JUGADORES, 
	NO_ENTRAN_MAS_JUGADORES;
}
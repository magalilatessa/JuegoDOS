package test.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.Carta;
import Modelo.Color;
import Modelo.CompararCarta;

class TestCarta {

	@Test
	void testCompararCarta() {
		Carta carta=new Carta(new String("8"), Color.AZUL);
		Carta carta1=new Carta(new String("8"), Color.AMARILLO);
		assertEquals(CompararCarta.IGUAL_NUMERO, carta.comparar(carta1));
	}

}

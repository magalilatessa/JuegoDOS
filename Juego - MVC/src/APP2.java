import Controlador.Controlador;
import Modelo.Juego;
import Vista.VistaConsola;
import Vista.VistaGrafica;

public class APP2 {

	public static void main(String[] args) {
		VistaConsola vista = new VistaConsola();
		Juego modelo = new Juego();
		//VistaGrafica vista=new VistaGrafica();
		Controlador controlador = new Controlador(modelo, vista);
		vista.iniciar();

	}

}

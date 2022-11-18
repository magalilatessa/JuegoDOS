import java.rmi.RemoteException;

import Modelo.Ijuego;
import Modelo.Juego;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.servidor.Servidor;

public class ServidorApp {

	public static void main(String[] args) {
			Ijuego modelo = new Juego();
			Servidor servidor = new Servidor("127.0.0.1", 64005);
			System.out.println("Iniciando servidor...");
			try {
				servidor.iniciar(modelo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RMIMVCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
}

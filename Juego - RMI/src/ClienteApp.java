import java.rmi.RemoteException;

import Controlador.Controlador;
import Vista.VistaConsola;
import ar.edu.unlu.rmimvc.RMIMVCException;
import ar.edu.unlu.rmimvc.cliente.Cliente;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;



public class ClienteApp {

	public static void main(String[] args) {
		VistaConsola vista = new VistaConsola();
		IControladorRemoto controlador = new Controlador(vista);
		Cliente cliente = new Cliente("127.0.0.1", 64008, "127.0.0.1", 64005);
		try {
			cliente.iniciar(controlador);
			vista.iniciar();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RMIMVCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}}


package Vista;

import java.rmi.RemoteException;

import Controlador.Controlador;

public interface Ivista {
	public void setControlador(Controlador controlador);

	public void iniciar() throws RemoteException;
}

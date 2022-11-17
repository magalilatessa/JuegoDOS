package Modelo;

import java.io.Serializable;
import java.util.List;

public interface Ijugador extends Serializable{
	public List<Carta> getCartas();
	public String getId();
}

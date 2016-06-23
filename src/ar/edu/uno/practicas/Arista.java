package ar.edu.uno.poo2.modulo;
import java.util.*;

public class Arista {
	private Vertice inicio;
	private Vertice destino;

	public Arista(Vertice a, Vertice b) {
		//Ocultamos los métodos para setear a y b.
		//Solamente se admite su pasaje al momento de
		//creación de la arista.
		this.setInicio(a); 
		this.setDestino(b);
	}

	//Getters & Setters
	public Vertice getInicio() {
		return inicio;
	}
	private void setInicio(Vertice a) {
		this.inicio = a;
	}
	public Vertice getDestino() {
		return destino;
	}
	private void setDestino(Vertice b) {
		this.destino = b;
	}
}

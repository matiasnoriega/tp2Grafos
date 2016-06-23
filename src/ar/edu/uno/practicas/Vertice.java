package ar.edu.uno.poo2.modulo;

import java.util.ArrayList;

public class Vertice {
	private ArrayList<Arista> aristas;
	private String nombre;
	
	public Vertice() {
	}
	
	private void setNombre(String n) {
		this.nombre = n;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void AddArista(Arista a) {
		aristas.add(a);
	}
}

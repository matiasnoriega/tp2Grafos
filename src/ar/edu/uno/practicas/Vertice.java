package ar.edu.uno.poo2.modulo;

import java.util.ArrayList;

public class Vertice {
	private Integer grado;
	
	private ArrayList<Arista> aristas;
	private String nombre;
	
	public Vertice() {
	}
	
	//getters y setters
	public Integer getGrado() {
		return grado;
	}
	public void setGrado(Integer grado) {
		this.grado = grado;
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

package ar.edu.uno.poo2.modulo;

import java.util.ArrayList;

public class Vertice {
	private Integer grado;
	private Integer numero;
	public Vertice(Integer numero) {
		setNumero(numero);
	}
	
	//getters y setters
	public Integer getGrado() {
		return grado;
	}
	public void setGrado(Integer grado) {
		this.grado = grado;
	}
	public Integer getNumero(){
		return this.numero;
	}
	public void setNumero(Integer numero){
		this.numero = numero;
	}
}

package ar.edu.uno.poo2.modulo;
import java.util.*;

public class Grafo {
	private Boolean[][] matrizAdyacencia;
	private Arista[] vectorAristas;
	private Vertice[] vectorVertices;
	private Integer dimension;


	public Grafo (int dimension, double probabilidad){
		setDimension(dimension);
		Boolean[][] matrizAuxiliar = new Boolean[dimension][dimension];
		Random r= new Random();
		for (int i=0; i<dimension; i++){
			matrizAuxiliar[i][i]=false;
			for (int j=i+1; j<dimension; j++){
				if (r.nextDouble()>probabilidad){
					matrizAuxiliar[i][j]=true;
					matrizAuxiliar[j][i]=true;
				}
				else{
					matrizAuxiliar[i][j]=false;
					matrizAuxiliar[j][i]=false;
				}
			}
		}
		this.matrizAdyacencia = matrizAuxiliar;
	}

	public Grafo(int dimension, int porcentaje){
		setVectorVertices(dimension);
		Boolean[][] matrizAuxiliar = new Boolean[dimension][dimension];

		//calculo la cantidad de aristas totales que va a tener Kn y las seteo
		Integer aristas = (dimension*(dimension-1)/2);
		setVectorAristas(aristas);
		Integer contadorAristas = 0;//contador que voy a usar mas adelante para llenar el vector de aristas

		//lleno el vector vertice con vertices vacios por ahora
		for (int i=0;i<dimension;i++){
			this.vectorVertices[i] = new Vertice();
		}
		//genero una matriz de adyacencia toda en true, para que me genere las aristas mas tarde. en la diagonal va a ser false.
		for (int i=0; i<dimension; i++){
			for (int j=0; j<dimension; j++){
				if (i!=j)
					matrizAuxiliar[i][j]=true;
				else
					matrizAuxiliar[i][j]=false;
			}
		}
		//recorro la mitad superior de la matriz de adyacencia para generar las aristas, pasando las posiciones del vector vertices
		for(int i=0;i<dimension;i++){
			for(int j=i+1;j<dimension;j++){
				this.vectorAristas[contadorAristas] = new Arista(this.vectorVertices[i], this.vectorVertices[j]);
				contadorAristas++;
			}
		}
		
		desordenarVector(aristas, this.vectorAristas);

		this.matrizAdyacencia = matrizAuxiliar;
	}

	public Arista[] desordenarVector(Integer dimension, Arista[] vectorAristas){

		Arista[] vectorAuxiliarArista = vectorAristas;
		Integer[] vectorAuxiliarDesorden = new Integer[dimension];    
		Integer n =0;      
		Random r = new Random();
		//creo un vector de numeros aleatorios del mismo tamaño que el de aristas
		for(int i=0; i<dimension; i++){
			n = r.nextInt(dimension); 
			vectorAuxiliarDesorden[i] = n; 
			vectorAuxiliarDesorden[i]++;
		}
		//ordeno ese vector de numeros aleatorios paralelamente al de aristas
		for (int i=1; i<dimension; i++){
			int index = vectorAuxiliarDesorden[i];
			int j = i;
			
			while (j > 0 && vectorAuxiliarDesorden[j-1] > index)
			{
				vectorAuxiliarDesorden[j] = vectorAuxiliarDesorden[j-1];
				vectorAuxiliarArista[j] = vectorAuxiliarArista[j-1];
				j--;
			}
			vectorAuxiliarDesorden[j] = index;
		}
		
		return vectorAuxiliarArista;

	}
	public int[] coloreoSecuencialAleatorio(){
		int colores[]= new int[this.matrizAdyacencia.length];
		colores[0]= 1;
		for (int i = 1 ; i < this.matrizAdyacencia.length; i++){

		}
		return colores;
	}

	//Getters y Setters
	public Integer getDimension() {
		return dimension;
	}
	public void setDimension(Integer dimension) {
		this.dimension = dimension;
	}
	public Arista[] getVectorAristas() {
		return vectorAristas;
	}
	public void setVectorAristas(Integer n) {
		this.vectorAristas = new Arista[n];
	}
	public Vertice[] getVectorVertices() {
		return vectorVertices;
	}
	public void setVectorVertices(Integer n) {
		this.vectorVertices = new Vertice[n];
	}


}

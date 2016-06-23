package ar.edu.uno.practicas;

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
			matrizAdyacencia[i][i]=false;
			for (int j=i+1; j<dimension; j++){
				if (r.nextDouble()>probabilidad){
					matrizAdyacencia[i][j]=true;
					matrizAdyacencia[j][i]=true;
				}
				else{
					matrizAdyacencia[i][j]=false;
					matrizAdyacencia[j][i]=false;
				}
			}
		}
		this.matrizAdyacencia = matrizAuxiliar;
	}
	
	public Grafo(int dimension, int porcentaje){
		Boolean[][] matrizAuxiliar = new Boolean[dimension][dimension];
		int aristas= 0;
		Random r = new Random();
		aristas = (dimension*(dimension-1)/2);
		
		
		this.matrizAdyacencia = matrizAuxiliar;
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

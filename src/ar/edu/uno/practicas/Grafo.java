package ar.edu.uno.poo2.modulo;
import java.util.*;

public class Grafo {
	private Boolean[][] matrizAdyacencia;
	private Arista[] vectorAristas;
	private Vertice[] vectorVertices;
	private Integer dimension;
	private Integer porcentaje;
	private int[] vectorColores;
	private int cantidadColores = 0;


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
		this.setPorcentaje(porcentaje);
		this.setVectorVertices(dimension);
		this.dimension=dimension;
		Boolean[][] matrizAuxiliar = new Boolean[dimension][dimension];

		//calculo la cantidad de aristas totales que va a tener Kn y las seteo
		Integer aristas = (dimension*(dimension-1)/2);
		setVectorAristas(aristas);
		Integer contadorAristas = 0;//contador que voy a usar mas adelante para llenar el vector de aristas

		//lleno el vector vertice con vertices vacios por ahora
		for (int i=0;i<dimension;i++){
				this.vectorVertices[i] = new Vertice(i);
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
		
		this.vectorAristas=desordenarVector(aristas, this.vectorAristas, porcentaje );
		
		
		this.matrizAdyacencia = generarMatrizAdy(matrizAuxiliar, dimension, this.vectorAristas);
	}

	public Boolean[][] generarMatrizAdy(Integer dimension, Arista[] vectorAristas){
		Boolean[][] matrizAuxAd = new Boolean[dimension][dimension];
		Arista[] vectorAuxAristas = vectorAristas;
		for(int i=0;i<dimension;i++){
			for (int j=0; j<dimension; j++){
				matrizAuxAd[i][j] = false;
			}
		}
		for(int i=0; i<vectorAristas.length;i++){
			matrizAuxAd[vectorAuxAristas[i].getInicio().getNumero()][vectorAuxAristas[i].getDestino().getNumero()]=true;
			matrizAuxAd[vectorAuxAristas[i].getDestino().getNumero()][vectorAuxAristas[i].getInicio().getNumero()]=true;
		}
		
		
		return matrizAuxAd;
		
	}
	public Arista[] desordenarVector(Integer dimension, Arista[] vectorAristas, Integer porcentaje){

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
		Integer nuevoTamano = (porcentaje*dimension)/100;
		Arista[] vectorTemporal = new Arista[nuevoTamano];
		for(int i=0;i<nuevoTamano;i++){
			vectorTemporal[i]=vectorAuxiliarArista[i];
		}
		
		return vectorTemporal;

	}
	
	public int[] coloreoSecuencialAleatorio(){
		int colores[]= new int[dimension];
		boolean iguales;
		boolean verticeNoColoreado;
		
		for (int i = 0 ; i < dimension; i++){
			//Al principio le asigno el color más  bajo posible
			// e inicializo las banderas para iniciar la comprobacion 
			// de vertices adyacentes con el mismo color
			colores[i]= 1;
			iguales= false;
			verticeNoColoreado= true;
			while (verticeNoColoreado){
				for (int j = i ; j >=0; j--){
					// Si son adyacentes compruebo los colores y de acuerdo a eso
					// cambio las banderas.
					if (matrizAdyacencia[j][i]){
						if (colores[i] == colores[j]){
							iguales=true;
						}
					}
					if (iguales){
						verticeNoColoreado=true;
						iguales= false;
						colores[i]++;
					}else{
						verticeNoColoreado=false;
					}
					if(colores[i]>this.cantidadColores)
						this.cantidadColores=colores[i];					
					
				}
			}
		}
		this.vectorColores = colores;
		return colores;
	}
	
	public void coloreoWelshPowell(){
		int grado[]= new int[dimension];
		int contador;
		int aux;
		int k;
		boolean fila[]= new boolean[dimension];
		Grafo grafoAux= this;
		for (int i=0; i< dimension; i++){
			contador=0;
			for (int j=i+1; j < dimension; j++){
				if (matrizAdyacencia[i][j])
					contador++;
			}
			grado[i]=contador;
		}
		for (int i=1; i < dimension; i++){
			aux=grado[i];
			for (int l=0; l< dimension; l++)
				fila[i]=grafoAux.matrizAdyacencia[i][l];
			k= i-1;
			while ((k>=0) && (aux< grado[k])){
				grado[k + 1]= grado[k];
				for (int l=0; l< dimension; l++)
					fila[k+1]=grafoAux.matrizAdyacencia[k][l];
				k--;
			}
			grado[k+1]= aux;
			for (int l=0; l< dimension; l++)
				grafoAux.matrizAdyacencia[k+1][l]=fila[i];
		}
		this.vectorColores =  grafoAux.coloreoSecuencialAleatorio();
	}
	
	public void coloreoMatula(){
		int grado[]= new int[dimension];
		int contador;
		int aux;
		int k;
		boolean fila[]= new boolean[dimension];
		Grafo grafoAux= this;
		for (int i=0; i< dimension; i++){
			contador=0;
			for (int j=i+1; j < dimension; j++){
				if (matrizAdyacencia[i][j])
					contador++;
			}
			grado[i]=contador;
		}
		for (int i=1; i < dimension; i++){
			aux=grado[i];
			for (int l=0; l< dimension; l++)
				fila[i]=grafoAux.matrizAdyacencia[i][l];
			k= i-1;
			while ((k>=0) && (aux> grado[k])){
				grado[k + 1]= grado[k];
				for (int l=0; l< dimension; l++)
					fila[k+1]=grafoAux.matrizAdyacencia[k][l];
				k--;
			}
			grado[k+1]= aux;
			for (int l=0; l< dimension; l++)
				grafoAux.matrizAdyacencia[k+1][l]=fila[i];
		}
		this.vectorColores = grafoAux.coloreoSecuencialAleatorio();
	}
	
	
	public String toString(){
		String string =this.dimension+" "+this.cantidadColores+" "+this.vectorAristas.length+" "+this.porcentaje+"\n";		
		for(int i =0; i<this.getDimension();i++){
			string += "Nodo: "+i+" Color: "+this.vectorColores[i]+"\n";
			
		}
		return string;
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
	public Integer getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

}

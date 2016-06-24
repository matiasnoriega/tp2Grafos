package 
import java.io.*;
import java.util.*;

public class Test {

	public static void main(String[] args) {
		Grafo grafo;
		File file= new File("grafo.in");
		FileReader fr = null;
		BufferedReader br;
		
		
		try{
			fr= new FileReader(file);
			br= new BufferedReader(fr);
			StringTokenizer linea= new StringTokenizer(br.readLine());
			int aristas= Integer.parseInt(linea.nextToken());
			int porcentajeAdyacencia= Integer.parseInt(linea.nextToken());
			grafo= new Grafo(aristas, porcentajeAdyacencia);
		}catch (FileNotFoundException e){
			System.out.println(file.getAbsolutePath());
			System.out.println("Error Al abrir el archivo");
		}catch (Exception e1){
			System.out.println(e1.getStackTrace());
		}
		finally{try{                    
			if( null != fr ){   
				fr.close();     
			}                  
		}catch (Exception e2){ 
			System.out.println(e2.getStackTrace());
		}	
		}
		// Esperando el toString()...
		FileWriter salida= null;
		PrintWriter pw = null;
		try{
			salida= new FileWriter("grafo.out");
			pw= new PrintWriter(salida);
			pw.println(".toString()");
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}finally{
			if (null!=pw)
				pw.close();
		}
	}

}

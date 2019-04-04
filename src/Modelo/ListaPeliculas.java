package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ListaPeliculas {
	
	
	private HashMap<Integer, String> lPelis;
	private static ListaPeliculas miListaPeliculas;
	
	public ListaPeliculas() {
		
		lPelis = new HashMap<Integer, String>();
	}
	
	public static ListaPeliculas getListaPeliculas() {
		if(miListaPeliculas==null) {
			miListaPeliculas=new ListaPeliculas();
		}
		return miListaPeliculas;
	}
	
	
	public static ArrayList<String> cargarDatosPelicula(String ruta) throws FileNotFoundException, IOException {
		      String cadena;
		      int cont=0;
		      FileReader f = new FileReader(ruta);
		      BufferedReader b = new BufferedReader(f);
		      ArrayList<String> datos=new ArrayList<String>();
		      while((cadena = b.readLine())!=null) {
		    	  datos.add(cont, cadena);
		          int i=0;
		          char c;          
		          c=cadena.charAt(i);
		          while(c!=';') {
		        	  c=cadena.charAt(i);
		        	  i++;  
		          }
		          String idS=cadena.substring(0,i-1);
		          int id=Integer.parseInt(idS);        
		          i=i+2;
		          int j=i;
		          while(c!='(') {
		        	  c=cadena.charAt(i);
		        	  i++;     
		          }
		          String titulo=cadena.substring(j-1,i-1);
		          i=cadena.length()-7;
		          j=i;
		          while(c!=')') {
		        	  c=cadena.charAt(i);
		        	  i++;
		          }
		          String añoS=cadena.substring(j+1,i-1);
		          int año=Integer.parseInt(añoS);
		          
		          //System.out.println(id + " " + titulo + " " + año );
		          
		          //Pelicula unaPeli=new Pelicula(id, titulo, año);
		          miListaPeliculas.getListaPeliculas().añadirPelicula(id, titulo);
		          cont++;
		          //System.out.println(datos.get(cont-1));
		          
		      }
		      b.close();
		      return datos;
		      
	}
	
	public void añadirPelicula(int pId, String pTitulo) {
		lPelis.put(pId, pTitulo);
	}
	


	/*public HashMap<Integer, String> obtenerDatos() {
		
		return lPelis;
	}*/
	
	public String getNombre(Integer pId) {
		
		return lPelis.get(pId);
	}
	
	/*public static void main(String[] args) throws FileNotFoundException, IOException {
		
		cargarDatosPelicula("C:\\Users\\mikel\\Desktop\\movie-titles.csv");
		
	}
	*/
	
	
}
    

	



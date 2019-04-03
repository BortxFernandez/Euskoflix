package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ListaValoraciones {

	private HashMap <Integer,ArrayList<Valoracion>> lValoraciones;
	private static ListaValoraciones miListaValoraciones;
	
	public ListaValoraciones() {
		lValoraciones = new HashMap<Integer,ArrayList<Valoracion>>();
	}

	public static ListaValoraciones getListaValoraciones() {
		if (miListaValoraciones==null){
			miListaValoraciones=new ListaValoraciones();
		}
		return miListaValoraciones;
	}
	
	public static ArrayList<String> cargarDatosValoracion(String ruta) throws FileNotFoundException, IOException {
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
	          while(c!=',') {
	        	  c=cadena.charAt(i);
	        	  i++;  
	          }
	          String idStringU=cadena.substring(0,i-1);
	          int idU=Integer.parseInt(idStringU);
	          i=i+1;
	          int j=i;
	          c=cadena.charAt(i);
	          while(c!=',') {
	        	  c=cadena.charAt(i);
	        	  i++;  
	          }
	          String idStringP=cadena.substring(j,i-1);
	          int idP=Integer.parseInt(idStringP);
	          String notaString=cadena.substring(i);
	          double nota= Double.parseDouble(notaString);
	          
	          Valoracion unaValoracion = new Valoracion(idP,nota);
	          miListaValoraciones.getListaValoraciones().annadirValoracion(idU, unaValoracion);
	          //System.out.println(idU + " " + idP + " " + nota);
	          cont++;
	      }
	          
	          //System.out.println(id + " " + titulo + " " + a�o );          
	      
	
	      b.close();
	      return datos;
		}
	
	public void annadirValoracion(int pIdUsuario, Valoracion v) {
		if (!lValoraciones.containsKey(pIdUsuario)) {
			ArrayList<Valoracion> lv = new ArrayList<Valoracion>();
			lv.add(v);
			lValoraciones.put(pIdUsuario,lv);
		}
		else {
			ArrayList<Valoracion> lv = lValoraciones.get(pIdUsuario);
			lv.add(v);
			lValoraciones.replace(pIdUsuario, lv);
		}
	}
	
	public HashMap <Integer,ArrayList<Valoracion>> obtenerDatosValoraciones() {
		return this.lValoraciones;
	}
	
	/*public static void main(String[] args){
		try {
			cargarDatosValoracion("C:\\Users\\borja\\Desktop\\Borja cosas\\Uni\\Tercero culo\\IS\\movie-ratings.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
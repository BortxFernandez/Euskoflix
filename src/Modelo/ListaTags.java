package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.javatuples.Pair;



public class ListaTags {
	
	
	private HashMap<Integer, Tag> lTags;
	private static ListaTags miListaTags;
	
	public ListaTags() {
		
		lTags = new HashMap<Integer, Tag>();
	}
	
	public static ListaTags getListaTags() {
		if(miListaTags==null) {
			miListaTags=new ListaTags();
		}
		return miListaTags;
	}
	
	
	public static ArrayList<String> cargarDatosTags(String ruta) throws FileNotFoundException, IOException {
		      String cadena;
		      FileReader f = new FileReader(ruta);
		      BufferedReader b = new BufferedReader(f);
		      String anterior = null;
		      int idAnterior = 0;
		      int n=1;
		      ArrayList<String> datos = new ArrayList<String>();
		      while((cadena = b.readLine())!=null) {
		    	  datos.add(cadena);
		          int i=0;
		          char c;          
		          c=cadena.charAt(i);
		          while(c!=';') {
		        	  c=cadena.charAt(i);
		        	  i++;  
		          }
		          String idS=cadena.substring(0,i-1);
		          int idActual=Integer.parseInt(idS);        
		          i=i+1;
		          int j=i;
	
		          String desc=cadena.substring(j-1);
		          if (desc.equals(anterior)){
		        	  n++;
		          }
		          else
		          {
		        	  Tag unTag = new Tag(n,anterior);
			          miListaTags.getListaTags().añadirTag(idAnterior, unTag);
			          n=1;
		          }
		          
		          anterior=desc;
		          idAnterior = idActual;
		         
		          
		          

		          
		      }
		      b.close();	
		      return datos;
		      
	}
	
	public void añadirTag(int i,Tag t) {
			lTags.put(i,t);	
	}
	


	

	

    
}
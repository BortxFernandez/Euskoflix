package Modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
	
	public void annadirValoracion(int pIdPeli, Valoracion v) {
		if (!lValoraciones.containsKey(pIdPeli)) {
			ArrayList<Valoracion> lv = new ArrayList<Valoracion>();
			lv.add(v);
			lValoraciones.put(pIdPeli,lv);
		}
		else {
			ArrayList<Valoracion> lv = lValoraciones.get(pIdPeli);
			lv.add(v);
			lValoraciones.replace(pIdPeli, lv);
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
	
	public ArrayList<Valoracion> obtenerListaValoraciones(int idPeli){
		
		ArrayList<Valoracion> lista=lValoraciones.get(idPeli);
		return lista;
		
	}
	
	public double estimarValoracion(int idPeli, int idUsu) {
		
		HashMap<Integer, Double> correlaciones=this.obtener35Correlaciones(idPeli);
		double suma1=0.00;
		double suma2=0.00;
		for (Entry<Integer, Double> entry : correlaciones.entrySet()) {
			double valoracion=this.obtenerValoracionPersona(entry.getKey(), idUsu);
			suma1=suma1+(valoracion*entry.getValue());
			suma2=suma2+entry.getValue();
		}
		
		return suma1/suma2;
		
	}
	
	//Implementar
	public double obtenerValoracionPersona(int pIdPeli, int pIdUsu) {
		double result=0.00;
		return result;
	}
	
	public HashMap<Integer, Double> obtener35Correlaciones(int idPeli) {
		
		HashMap<Integer, Double> correlaciones=new HashMap<Integer, Double>();
		HashMap<Integer, String> lPelis=ListaPeliculas.getListaPeliculas().obtenerPelis();
		for (Entry<Integer, String> entry : lPelis.entrySet()) {
			int idPeli2=entry.getKey();
			if(idPeli!=idPeli2) {
					double correlacion=calcularCorrelacion(idPeli, idPeli2);
					double correlacionTrans=transformarCorrelacion(correlacion);
					correlaciones.put(idPeli2, correlacionTrans);	
			}
		}
		
		HashMap<Integer,Double> aux = (HashMap<Integer, Double>) correlaciones.clone();
		HashMap<Integer, Double> mayores=new HashMap<Integer, Double>();
		boolean lleno=false;
	
		while (!lleno){
		
			double maxValueInMap=(Collections.max(correlaciones.values()));  // This will return max value in the Hashmap
        
        	for(Iterator<Map.Entry<Integer,Double>> it = aux.entrySet().iterator();it.hasNext();){
        		Map.Entry<Integer, Double> entry = it.next();
        		if (entry.getValue()==maxValueInMap) {
        			if (mayores.size()<35){
        				mayores.put(entry.getKey(), entry.getValue());
            			it.remove();
        			}
        			else{
        				lleno=true;
        			}	
        		}
        	}	
        }
		
		return mayores;
	}
	
	/*public HashMap<Integer, Double> obtener35Correlaciones(int idPeli) {
		HashMap<Integer, Double> correlaciones=new HashMap<Integer, Double>();
		HashMap<Integer, String> lPelis=ListaPeliculas.getListaPeliculas().obtenerPelis();
		for (Entry<Integer, String> entry : lPelis.entrySet()) {
			int idPeli2=entry.getKey();
			if(idPeli!=idPeli2) {
				double correlacion=calcularCorrelacion(idPeli, idPeli2);
				double coTrans=transformarCorrelacion(correlacion);	
				correlaciones.put(idPeli2, coTrans);
			}
		}
		HashMap<Integer, Double> mayores=new HashMap<Integer, Double>();
		Double max=0.00;
		int key=-1;
		HashMap<Integer, Double> aux=correlaciones;
		while(mayores.size()!=35) {
			correlaciones=aux;
			for (Entry<Integer, Double> entry : correlaciones.entrySet()) {
				if(entry.getValue()>=max) {
					max=entry.getValue();
					key=entry.getKey();
				}	
			}
			mayores.put(key, max);
			aux.remove(key);
		}
		return mayores;
		
	}*/
	
	public double transformarCorrelacion(double pCo) {
		double res=pCo;
		
		if(res>0) {
			res=1-res;
		}
		else {
			res=res+1;
		}
		return res;
	}
	
	public double calcularCorrelacion(int idPeli1,int idPeli2) {
		
		
		ArrayList<Valoracion> lista1=this.obtenerListaValoraciones(idPeli1);
		ArrayList<Valoracion> lista2=this.obtenerListaValoraciones(idPeli2);
		
		double sx = 0.0;
	    double sy = 0.0;
	    double sxx = 0.0;
	    double syy = 0.0;
	    double sxy = 0.0;

	    int n = lista1.size();

	    for(int i = 0; i < n; i++) {
	      double x = lista1.get(i).getNota();
	      double y = lista2.get(i).getNota();

	      sx += x;
	      sy += y;
	      sxx += x * x;
	      syy += y * y;
	      sxy += x * y;
	    }

	    // covariation
	    double cov = sxy / n - sx * sy / n / n;
	    // standard error of x
	    double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
	    // standard error of y
	    double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);

	    // correlation is just a normalized covariation
	    return cov / sigmax / sigmay;
		

	}

}
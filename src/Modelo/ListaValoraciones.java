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
	          String idStringP=cadena.substring(j-1,i-1);
	          int idP=Integer.parseInt(idStringP);
	          String notaString=cadena.substring(i);
	          double nota= Double.parseDouble(notaString);
	          
	          Valoracion unaValoracion = new Valoracion(idU,nota);
	          miListaValoraciones.getListaValoraciones().annadirValoracion(idP, unaValoracion);

	          
	          //System.out.println(idU + " " + idP + " " + nota);
	          
	          cont++;
	      }
	             
	      
	
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
			//System.out.println(valoracion);
			suma1=suma1+(valoracion*entry.getValue());
			suma2=suma2+entry.getValue();
		}
		
		return suma1/suma2;
		
	}
	
	//Implementar
	public double obtenerValoracionPersona(int pIdPeli, int pIdUsu) {
		double result=0.00;
		if(lValoraciones.containsKey(pIdPeli)) {
			ArrayList<Valoracion> lista= this.obtenerListaValoraciones(pIdPeli);
			int i=0;
			boolean encontrado=false;
			while(i<lista.size() && !encontrado) {
				//System.out.println(lista.get(i).getId());
				//System.out.println(lista.get(i).getId()==pIdUsu);
				if(lista.get(i).getId()==pIdUsu) {
					result=lista.get(i).getNota();
					encontrado=true;
					//System.out.println(result);
				}
				else{
					i++;
				}
			}
		}
		//System.out.println(result);
		
		return result;
	}
	
	public HashMap<Integer, Double> obtener35Correlaciones(int idPeli) {
		
		HashMap<Integer, Double> correlaciones=new HashMap<Integer, Double>();
		HashMap<Integer, String> lPelis=ListaPeliculas.getListaPeliculas().obtenerPelis();
		for (Entry<Integer, String> entry : lPelis.entrySet()) {
			int idPeli2=entry.getKey();
			//System.out.println(idPeli2);
			if(idPeli!=idPeli2 && lValoraciones.containsKey(idPeli2)) {
					double correlacion=calcularCorrelacion(idPeli, idPeli2);
					//System.out.println(correlacion);
					double correlacionTrans=transformarCorrelacion(correlacion);
					correlaciones.put(idPeli2, correlacionTrans);
					//System.out.println(correlacionTrans);
					
			}
		}
		
		HashMap<Integer,Double> aux = (HashMap<Integer, Double>) correlaciones.clone();
		HashMap<Integer, Double> mayores=new HashMap<Integer, Double>();
		boolean lleno=false;
		int limite=0;
		if(aux.size()<36) {
			limite=aux.size();
		}
		else {
			limite=35;
		}
		while (!lleno){
		
			double maxValueInMap=(Collections.max(aux.values()));  // This will return max value in the Hashmap
        
        	for(Iterator<Map.Entry<Integer,Double>> it = aux.entrySet().iterator();it.hasNext();){
        		Map.Entry<Integer, Double> entry = it.next();
        		if (entry.getValue()==maxValueInMap) {
        			if (mayores.size()<limite){
        				mayores.put(entry.getKey(), entry.getValue());
        				//System.out.println(entry.getValue());
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
		
		
		double x=0.00;
		double y=0.00;
		double sx = 0.0;
	    double sy = 0.0;
	    double sxx = 0.0;
	    double syy = 0.0;
	    double sxy = 0.0;
	    double cov=0.0;
	    double sigmax=0.0;
	    double sigmay=0.0;
	    
	    
	    if(lista1!=null && lista2!=null) {	
			for (int i=0; i<lista1.size(); i++){
				//System.out.println(lista1.get(i));
				int k=0;
				ArrayList<Valoracion> lista3=(ArrayList<Valoracion>) lista2.clone();
				boolean acabado=false;
				while(!acabado && k<lista3.size()) {
					if(lista1.get(i).getId()==lista3.get(k).getId()) {
						y = lista3.get(k).getNota();
						lista2.remove(k);
						acabado=true;
					}
					else {
						k++;
					}	
				}
				if(!acabado) {
					
					y=0.00;
				}
				x = lista1.get(i).getNota();
				
				sx += x;
			    sy += y;
			    sxx += x * x;
			    syy += y * y;
			    sxy += x * y;
			    
			    
			}
			for(int j=0; j<lista2.size()-1;j++) {
				y=lista2.get(j).getNota();
				x=0.00;
				sx += x;
			    sy += y;
			    sxx += x * x;
			    syy += y * y;
			    sxy += x * y;		
			}
			int n=lista1.size() + lista2.size();
			
			// covariation
			cov = sxy / n - sx * sy / n / n;
			// standard error of x
			sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
			// standard error of y
			sigmay = Math.sqrt(syy / n -  sy * sy / n / n);
	    }	
		// correlation is just a normalized covariation
		return cov / sigmax / sigmay;
	      
	}
	


}
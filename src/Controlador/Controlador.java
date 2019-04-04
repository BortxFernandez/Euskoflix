package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;

import Vista.VentanaPeliculasCargadas;
import Vista.VentanaPrincipal;
import Vista.VentanaTags;
import Vista.VentanaValoraciones;
import Controlador.Controlador;
import Modelo.ListaPeliculas;
import Modelo.ListaTags;
import Modelo.ListaValoraciones;
import Modelo.Tag;
import Modelo.Valoracion;

public class Controlador {
	
	private static Controlador miControlador;
	
	//Modelo
	

	//Vista
	private VentanaPrincipal ventanaPrincipal;
	private VentanaPeliculasCargadas ventanaPeliculasCargadas;
	private VentanaTags ventanaTags;
	private VentanaValoraciones ventanaValoraciones;
	
	public Controlador() {
		

		//Crear las ventanas
		
		
		this.ventanaPrincipal=new VentanaPrincipal();
		this.ventanaPeliculasCargadas=new VentanaPeliculasCargadas();
		this.ventanaTags=new VentanaTags();
		this.ventanaValoraciones=new VentanaValoraciones();
		
		
		//Añadir listeners a las ventanas
		this.ventanaPrincipal.addVerPeliculasListener(new VerPeliculasListener());
		this.ventanaPrincipal.addVerTagsListener(new VerTagsListener());
		this.ventanaPrincipal.addVerValoracionesListener(new VerValoracionesListener());
		
	}
	
	public static Controlador getControlador() {
		if (miControlador == null) {
        	miControlador = new Controlador();
        }
        return miControlador;
	}
	
	public void iniciarAplicacion() {
		this.mostrarVentanaPrincipal();
	}
	
	public void mostrarVentanaPrincipal() {
		this.ventanaPrincipal.setVisible(true);
	}
	public void mostrarVentanaPeliculasCargadas(ArrayList<String> pDatos) {
		
		
		
		this.ventanaPeliculasCargadas = new VentanaPeliculasCargadas();
		this.ventanaPeliculasCargadas.añadirPelis(pDatos);
		this.ventanaPeliculasCargadas.setVisible(true);
	}
	
	public void mostrarVentanaTagsCargados(ArrayList<String> pDatos) {
		
		this.ventanaTags = new VentanaTags();
		this.ventanaTags.añadirTags(pDatos);
		this.ventanaTags.setVisible(true);
	}
	
	//un cambio
	
	
	
	public void mostrarVentanaValoraciones(ArrayList<String> pDatos) {
		
		this.ventanaValoraciones=new VentanaValoraciones();
		this.ventanaValoraciones.añadirValoraciones(pDatos);
		this.ventanaValoraciones.setVisible(true);
	}
	
	
	//Listeners implementados
	class VerPeliculasListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			 String ruta="C:\\Users\\mikel\\Desktop\\movie-titles.csv";
			 ArrayList<String> datos=new ArrayList<String>();
			try {
				datos=ListaPeliculas.getListaPeliculas().cargarDatosPelicula(ruta);
				mostrarVentanaPeliculasCargadas(datos);
			} catch (IOException e1) {
				//System.out.println("No se han podido cargar las peliculas");
				e1.printStackTrace();
			}
			
		
		}	
	}
	
	class VerValoracionesListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String rutaPelis="C:\\Users\\mikel\\Desktop\\movie-titles.csv";
			String rutaValoraciones="C:\\Users\\mikel\\Desktop\\movie-ratings.csv";
			ArrayList<String> datosPeli=new ArrayList<String>();
			ArrayList<String> datosVal=new ArrayList<String>();
			try {
				datosPeli=ListaPeliculas.getListaPeliculas().cargarDatosPelicula(rutaPelis);
				datosVal=ListaValoraciones.getListaValoraciones().cargarDatosValoracion(rutaValoraciones);
				mostrarVentanaValoraciones(datosVal);
			} catch (IOException e1) {
				//System.out.println("No se han podido cargar las valoraciones");
				e1.printStackTrace();
			}
			
			
		}	
	}
	
	class VerTagsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String rutaPelis="C:\\Users\\mikel\\Desktop\\movie-titles.csv";
			String rutaTags="C:\\Users\\mikel\\Desktop\\movie-tags.csv";
			try {
				ArrayList<String> datosPelis = ListaPeliculas.getListaPeliculas().cargarDatosPelicula(rutaPelis);
				ArrayList<String> datosTags = ListaTags.getListaTags().cargarDatosTags(rutaTags);
				
				mostrarVentanaTagsCargados(datosTags);
			} catch (IOException e1) {
				//System.out.println("No se han podido cargar los Tags");
				e1.printStackTrace();
			}
			
		
		}	
	}
	
	
	

}

package Principal;

import java.io.IOException;
import java.util.ArrayList;

import Controlador.Controlador;
import Modelo.ListaPeliculas;
import Modelo.ListaTags;
import Modelo.ListaValoraciones;

public class Euskoflix {
	
	public static void main(String[] args) {
        //Controlador controlador = new Controlador();
        //controlador.iniciarAplicacion();
		
		//Poner id de peli y usuario a estimar
		int idPeli=161;
		int idUsu=2048;
		
		try {
			ArrayList<String> datosPelis=ListaPeliculas.getListaPeliculas().cargarDatosPelicula("C:\\Users\\mikel\\Desktop\\movie-titles.csv");
			ArrayList<String> datosValoraciones=ListaValoraciones.cargarDatosValoracion("C:\\Users\\mikel\\Desktop\\movie-ratings.csv");
			ArrayList<String> datosTags=ListaTags.getListaTags().getListaTags().cargarDatosTags("C:\\Users\\mikel\\Desktop\\movie-tags.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Datos cargados");
		double res=ListaValoraciones.getListaValoraciones().estimarValoracion(idPeli, idUsu);
		System.out.println("La valoracion que el usuario " + idUsu + " le daría a la película " + ListaPeliculas.getListaPeliculas().getNombre(idPeli) + " es " + res);
		
    }

}

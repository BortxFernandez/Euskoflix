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
		int idPeli=114;
		int idUsu=1;
		
		try {
			ArrayList<String> datosPelis=ListaPeliculas.getListaPeliculas().cargarDatosPelicula("C:\\Users\\mikel\\Desktop\\movie-titles.csv");
			ArrayList<String> datosValoraciones=ListaValoraciones.cargarDatosValoracion("C:\\Users\\mikel\\Desktop\\movie-ratings.csv");
			ArrayList<String> datosTags=ListaTags.getListaTags().getListaTags().cargarDatosTags("C:\\Users\\mikel\\Desktop\\movie-tags.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		double res=ListaValoraciones.getListaValoraciones().estimarValoracion(idPeli, idUsu);
		System.out.println(res);
    }

}

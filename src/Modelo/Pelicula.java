package Modelo;

public class Pelicula {
	
	private int id;
	private String titulo;
	private int a�o;
	
	public Pelicula(int pId, String pTitulo, int pA�o) {
		
		id=pId;
		titulo=pTitulo;
		a�o=pA�o;
	}
	
	public String getNombre(int pId) {
		
		return this.titulo;
	}

}

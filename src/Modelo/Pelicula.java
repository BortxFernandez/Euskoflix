package Modelo;

public class Pelicula {
	
	private int id;
	private String titulo;
	private int año;
	
	public Pelicula(int pId, String pTitulo, int pAño) {
		
		id=pId;
		titulo=pTitulo;
		año=pAño;
	}
	
	public String getNombre(int pId) {
		
		return this.titulo;
	}

}

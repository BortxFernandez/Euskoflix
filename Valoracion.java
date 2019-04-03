package Modelo;

public class Valoracion {
	
	private int idPelicula;
	private double nota;
	
	public Valoracion(int pId, double pNota) {
		idPelicula = pId;
		if (pNota==0.5||pNota==1.0||pNota==1.5||pNota==2.0||pNota==2.5||pNota==3.0||pNota==3.5||pNota==4.0||pNota==4.5||pNota==5.0) {
			nota = pNota;
		}
	}
	
	public int getId() {
		return idPelicula;
	}
	
	public double getNota() {
		return nota;
	}

}

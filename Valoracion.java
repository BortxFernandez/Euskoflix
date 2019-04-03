package Modelo;

public class Valoracion {
	
	private int idUsuario;
	private double nota;
	
	public Valoracion(int pId, double pNota) {
		idUsuario = pId;
		if (pNota==0.5||pNota==1.0||pNota==1.5||pNota==2.0||pNota==2.5||pNota==3.0||pNota==3.5||pNota==4.0||pNota==4.5||pNota==5.0) {
			nota = pNota;
		}
	}
	
	public int getId() {
		return idUsuario;
	}
	
	public double getNota() {
		return nota;
	}

}

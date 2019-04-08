package Modelo;

import java.util.ArrayList;

public class ListaUsuarios {
	
	private static ListaUsuarios miListaUsuarios;
	private ArrayList<Usuario> lUsuarios;
	
	public ListaUsuarios() {
		lUsuarios= new ArrayList<Usuario>();
	}
	
	public static ListaUsuarios getListaUsuarios() {
		if(miListaUsuarios==null) {
			miListaUsuarios=new ListaUsuarios();
		}
		return miListaUsuarios;
	}
	
	

}

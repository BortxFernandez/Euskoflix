package Modelo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class listaValoracionesPrueba {
	
	ListaValoraciones l = ListaValoraciones.getListaValoraciones();
	Valoracion v1 = new Valoracion(1,1);
	Valoracion v2 = new Valoracion(2,2);
	Valoracion v3 = new Valoracion(3,3);
	Valoracion v4 = new Valoracion(4,4);
	Valoracion v5 = new Valoracion(5,5);
	
	
	@Before
	public void setUp() throws Exception {
		l.annadirValoracion(1, v1);
		l.annadirValoracion(1, v2);
		l.annadirValoracion(1, v3);
		l.annadirValoracion(1, v4);
		l.annadirValoracion(1, v5);
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testObtenerValoracionPersona() {
		double d = l.obtenerValoracionPersona(1, 2);
		assertEquals(d,2,0.001);
		System.out.println("Deberia dar 2.0 y el resultado es: " + d);
		d = l.obtenerValoracionPersona(1, 3);
		assertEquals(d,3,0.001);
		System.out.println("Deberia dar 3.0 y el resultado es: " + d);
		d = l.obtenerValoracionPersona(2,1);
		assertEquals(d,0.0,0.001);
		System.out.println("Deberia dar 0.0 y el resultado es: " + d);
		d=l.obtenerValoracionPersona(1, 6);
		assertEquals(d,0.0,0.001);
		System.out.println("Deberia dar 0.0 y el resultado es: " + d);
	}


	@Test
	public void testTransformarCorrelacion() {
		double d = l.transformarCorrelacion(1.0);
		assertEquals(d,0.0,0.1);
		System.out.println("Deberia dar 0.0 y el resultado es: " + d);
		d = l.transformarCorrelacion(-1.0);
		assertEquals(d,0.0,0.1);
		System.out.println("Deberia dar 0.0 y el resultado es: " + d);
		d = l.transformarCorrelacion(0.1);
		assertEquals(d,0.9,0.01);
		System.out.println("Deberia dar 0.9 y el resultado es: " + d);
		
	}


}

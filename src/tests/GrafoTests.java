package tests;

import org.junit.Test;

import Generador.Generador;
import Generador.Npartito;
import Generador.Porcentaje;
import Generador.Probabilidad;
import Generador.Regular;
import Generador.RegularAdyacencia;

public class GrafoTests {

	@Test
	public void probabilidadTest() {
		Generador gen = new Probabilidad(100, 75);
		
		try {
			gen.generar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}
	
	
	@Test
	public void porcentajeTest() {
		Generador gen = new Porcentaje(600, 80);
		
		try {
			gen.generar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}
	
	@Test
	public void regularTest() {
		Generador gen = new Regular(10000, 9999);
		try {
			gen.generar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}
	
	@Test
	public void regularAdyacenciaTest() {
		Generador gen = new RegularAdyacencia(10000, 90);
		try {
			gen.generar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}
	
	@Test
	public void NpartitoTest() {
		Generador gen = new Npartito(12, 3);
		try {
			gen.generar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}
	
	
	
	
}


package tests;

import org.junit.Test;

import generador.Generador;
import generador.Npartito;
import generador.Porcentaje;
import generador.Probabilidad;
import generador.Regular;
import generador.RegularAdyacencia;

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
		Generador gen = new Npartito(15, 4);
		try {
			gen.generar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}

}

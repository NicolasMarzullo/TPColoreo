package tests;

import org.junit.Test;

import Generador.Generador;
import Generador.Probabilidad;
import Generador.RegularAdyacencia;

public class GrafoTests {

	@Test
	public void probabilidadTest() {
		Generador gen = new RegularAdyacencia(100, 75);
		gen.generar();
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}
}


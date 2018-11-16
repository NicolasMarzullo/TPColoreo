package tests;

import org.junit.Test;

import Generador.Generador;
import Generador.Probabilidad;
import Generador.Regular;

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
	
}


package tests;

import org.junit.Test;

import Generador.Generador;
import Generador.RegularAdyacencia;
import Generador.Regular;

public class GrafoTests {

//	@Test
//	public void probabilidadTest() {
//		Generador gen = new RegularAdyacencia(100, 75);
//		
//		try {
//			gen.generar();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		gen.calcularParametrosDelGrafo();
//		System.out.println(gen);
//	}
	
	
	@Test
	public void regularTest() {
		Generador gen = new Regular(6, 3);
		try {
			gen.generar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		gen.calcularParametrosDelGrafo();
		System.out.println(gen);
	}
	
}


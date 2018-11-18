package coloreo;

import java.io.FileNotFoundException;

import generador.Generador;
import generador.Porcentaje;

public class App {

	public static void main(String[] args) {
		// ANALISIS ESTADISTICO PARA GRAFOS ALEATORIOS DE 600 NODOS
		int[] resultado40Ady = new int[600];
		try {
				Generador generador = new Porcentaje(600, 40);
				generador.calcularParametrosDelGrafo();
				generador.escribirEnArchivo("40Ady");
				GrafoNPND grafoNPND = new GrafoNPND("40Ady");
				grafoNPND.colorearSecuencial(10000);
				grafoNPND.colorearMatula(10000);
				grafoNPND.colorearWheelsPower(10000);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

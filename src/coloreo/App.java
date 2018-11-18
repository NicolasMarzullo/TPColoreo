package coloreo;

import generador.Generador;
import generador.Porcentaje;

public class App {

	public static void main(String[] args) {
		// ANALISIS ESTADISTICO PARA GRAFOS ALEATORIOS DE 600 NODOS
		try {
				Generador generador = new Porcentaje(600,40);
				generador.generar();
				generador.calcularParametrosDelGrafo();
				generador.escribirEnArchivo("600Nodos_40Ady");
				System.out.println(generador.toString());
				GrafoNPND grafoNPND = new GrafoNPND("600Nodos_40Ady");
				grafoNPND.colorearSecuencial(10000);
				System.out.println("Secuencial " + grafoNPND.cantidadMejorDeColores);
				grafoNPND.colorearMatula(10000);
				System.out.println("Matula " + grafoNPND.cantidadMejorDeColores);
				grafoNPND.colorearWheelsPower(10000);
				System.out.println("Wheels Power " +  grafoNPND.cantidadMejorDeColores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

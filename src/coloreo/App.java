package coloreo;

import generador.Generador;
import generador.Porcentaje;
import generador.RegularAdyacencia;

public class App {

	public static void main(String[] args) {
		
		try {
				System.out.println("GRAFOS ALEATORIOS");
				// ANALISIS ESTADISTICO PARA GRAFOS ALEATORIOS DE 600 NODOS
				System.out.println("40 Adyacencia");
				Generador generador40 = new Porcentaje(600,40);
				generador40.generar();
				generador40.calcularParametrosDelGrafo();
				generador40.escribirEnArchivo("600Nodos_40Ady");
				//System.out.println(generador40.toString());
				GrafoNPND grafoNPND40 = new GrafoNPND("600Nodos_40Ady");
				grafoNPND40.colorearSecuencial(10000);
				System.out.println("Secuencial " + grafoNPND40.cantidadMejorDeColores);
				grafoNPND40.colorearMatula(10000);
				System.out.println("Matula " + grafoNPND40.cantidadMejorDeColores);
				grafoNPND40.colorearWheelsPower(10000);
				System.out.println("Wheels Power " +  grafoNPND40.cantidadMejorDeColores);
				System.out.println("Fin 40 Adyacencia");
				
				System.out.println("50 Adyacencia");
				Generador generador50 = new Porcentaje(600,50);
				generador50.generar();
				generador50.calcularParametrosDelGrafo();
				generador50.escribirEnArchivo("600Nodos_50Ady");
				//System.out.println(generador50.toString());
				GrafoNPND grafoNPND50 = new GrafoNPND("600Nodos_50Ady");
				grafoNPND50.colorearSecuencial(10000);
				System.out.println("Secuencial " + grafoNPND50.cantidadMejorDeColores);
				grafoNPND50.colorearMatula(10000);
				System.out.println("Matula " + grafoNPND50.cantidadMejorDeColores);
				grafoNPND50.colorearWheelsPower(10000);
				System.out.println("Wheels Power " +  grafoNPND50.cantidadMejorDeColores);
				System.out.println("Fin 50 Adyacencia");
				
				System.out.println("60 Adyacencia");
				Generador generador60 = new Porcentaje(600,60);
				generador60.generar();
				generador60.calcularParametrosDelGrafo();
				generador60.escribirEnArchivo("600Nodos_60Ady");
				//System.out.println(generador60.toString());
				GrafoNPND grafoNPND60 = new GrafoNPND("600Nodos_60Ady");
				grafoNPND60.colorearSecuencial(10000);
				System.out.println("Secuencial " + grafoNPND60.cantidadMejorDeColores);
				grafoNPND60.colorearMatula(10000);
				System.out.println("Matula " + grafoNPND60.cantidadMejorDeColores);
				grafoNPND60.colorearWheelsPower(10000);
				System.out.println("Wheels Power " +  grafoNPND60.cantidadMejorDeColores);
				System.out.println("Fin 60 Adyacencia");
				System.out.println("FIN GRAFOS ALEATORIOS");
				
				//GRAFOS REGULARES
				System.out.println("\nGRAFOS REGULARES");
				System.out.println("Regular 50 Adyacencia");
				Generador generadorReg50 = new RegularAdyacencia(1000,50);
				generadorReg50.generar();
				generadorReg50.calcularParametrosDelGrafo();
				generadorReg50.escribirEnArchivo("1000Nodos_50Ady");
				//System.out.println(generadorReg50.toString());
				GrafoNPND grafoNPNDReg50 = new GrafoNPND("1000Nodos_50Ady");
				grafoNPNDReg50.colorearSecuencial(10000);
				System.out.println("Secuencial " + grafoNPNDReg50.cantidadMejorDeColores);
				grafoNPNDReg50.colorearMatula(10000);
				System.out.println("Matula " + grafoNPNDReg50.cantidadMejorDeColores);
				grafoNPNDReg50.colorearWheelsPower(10000);
				System.out.println("Wheels Power " +  grafoNPNDReg50.cantidadMejorDeColores);
				
				System.out.println("Regular 75 Adyacencia");
				Generador generadorReg75 = new RegularAdyacencia(1000,75);
				generadorReg75.generar();
				generadorReg75.calcularParametrosDelGrafo();
				generadorReg75.escribirEnArchivo("1000Nodos_75Ady");
				//System.out.println(generadorReg50.toString());
				GrafoNPND grafoNPNDReg75 = new GrafoNPND("1000Nodos_75Ady");
				grafoNPNDReg75.colorearSecuencial(10000);
				System.out.println("Secuencial " + grafoNPNDReg75.cantidadMejorDeColores);
				grafoNPNDReg75.colorearMatula(10000);
				System.out.println("Matula " + grafoNPNDReg75.cantidadMejorDeColores);
				grafoNPNDReg75.colorearWheelsPower(10000);
				System.out.println("Wheels Power " +  grafoNPNDReg75.cantidadMejorDeColores);

				System.out.println("FIN GRAFOS REGULARES");
				
				//FIN GRAFOS REGULARES
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package coloreo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import generador.Generador;
import representacionAdyacencia.MatrizSimetrica;

public class GrafoNPND {

	private MatrizSimetrica matrizAdyacencia;
	private List<Nodo> nodos;
	private int cantidadDeColoresCorridaActual = 0;
	private int cantidadMejorDeColores = 0;
	private int numeroDeCorridaMejorCantidadColores = 0;
	private int[] resultadoDeCorrida;

	/*
	 * public GrafoNPND(Generador g) { this.matrizAdyacencia = g.getMatriz();
	 * this.nodos = new ArrayList<>();
	 * 
	 * // Cargo los nodos for (int i = 0; i < g.getCantidadNodos(); i++) {
	 * nodos.add(new Nodo(i)); } }
	 */

	public GrafoNPND(String path) {

	}

	public SalidaColoreo colorear() {
		return null;
	}

	public void colorearSecuencial(int cantidadDeVecesACorrer) {
		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(nodos);
			this.colorear();
			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual]++;
		}
	}

	public void colorearMatula(int cantidadDeVecesACorrer) {
		this.colorear();
	}

	public void colorearWheelsPower(int cantidadDeVecesACorrer) {
		this.colorear();
	}
}

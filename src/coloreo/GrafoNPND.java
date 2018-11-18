package coloreo;

import java.util.Collections;
import java.util.List;
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

	public SalidaColoreo colorear() {
		// Uso algoritmo que pinta nodo por nodo (una sola pasada).
		int colorActual = 1;
		int colorMax = 0;
		
		for (Nodo nodo1 : this.nodos) {
			for (Nodo nodo2 : this.nodos) {
				if (this.matrizAdyacencia.get(nodo1.id, nodo2.id) == 1) {
					// veo de qué color lo pinto
					if(nodo2.color != nodo1.color && nodo2.color != 0) {
						colorActual++;
						colorMax = colorActual;
					}

				}
			}
			nodo1.pintar(colorActual);
			colorActual = 1;
		}

		return new SalidaColoreo(this.nodos, colorMax);
	}

	public void colorearMatula(int cantidadDeVecesACorrer) {
		this.colorear();
	}

	public void colorearWheelsPower(int cantidadDeVecesACorrer) {
		this.colorear();
	}
}

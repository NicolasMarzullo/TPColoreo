package coloreo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import generador.Generador;
import representacionAdyacencia.MatrizSimetrica;

public class GrafoNPND {

	private MatrizSimetrica matrizAdyacencia;
	private List<Nodo> nodos;
	private Generador generador;

	public GrafoNPND(Generador g) {
		this.matrizAdyacencia = g.getMatriz();
		this.generador = g;
		this.nodos = new ArrayList<>();

		// Cargo los nodos
		for (int i = 0; i < g.getCantidadNodos(); i++) {
			nodos.add(new Nodo(i));
		}
	}

	public SalidaColoreo colorear(List<Integer> secuenciaDeRecorrido) {
		// Uso algoritmo que pinta nodo por nodo (una sola pasada).
		List<Integer> coloresUsados = new LinkedList<>();
		boolean pintar = false;
		int cantNodosPintados = 0, colorActual = 1;

		for (Integer nodo1 : secuenciaDeRecorrido) {
			for (Nodo nodo2 : this.nodos) {
				if (this.matrizAdyacencia.get(nodo1, nodo2.id) == 1) {
					//veo de qué color lo pinto
					
				}

			}
		}

		return new SalidaColoreo(this.nodos, 5);
	}

}

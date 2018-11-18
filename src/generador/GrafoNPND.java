package generador;

import java.util.LinkedList;
import java.util.List;

import representacionAdyacencia.MatrizSimetrica;

public class GrafoNPND {

	private MatrizSimetrica matrizAdyacencia;
	private List<Nodo> nodos;
	private Generador generador;

	public GrafoNPND(Generador g) {
		this.matrizAdyacencia = g.matriz;
		this.generador = g;
		this.nodos = new LinkedList<>();

		// Cargo los nodos
		for (int i = 0; i < g.cantidadNodos; i++) {
			nodos.add(new Nodo(i));
		}
	}

	public SalidaColoreo colorear(List<Integer> secuenciaDeRecorrido) {
		return new SalidaColoreo(this.nodos, 5);
	}

}

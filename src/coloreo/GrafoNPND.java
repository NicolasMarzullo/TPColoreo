package coloreo;

import java.util.ArrayList;
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
		return new SalidaColoreo(this.nodos, 5);
	}

}

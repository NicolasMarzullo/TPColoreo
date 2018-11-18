package generador;

import java.util.List;

import representacionAdyacencia.MatrizSimetrica;

public class GrafoNPND {
	
	private MatrizSimetrica matrizAdyacencia;
	private List<Nodo> nodos;
	
	
	public GrafoNPND(MatrizSimetrica matrizAdyacencia, List<Nodo> nodos) {
		this.matrizAdyacencia = matrizAdyacencia;
		this.nodos = nodos;
	}
	
	
	public SalidaColoreo colorear(List<Integer> secuenciaDeRecorrido) {
		return new SalidaColoreo(this.nodos, 5);
	}
	
	
}

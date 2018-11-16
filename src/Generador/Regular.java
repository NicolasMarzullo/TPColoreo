package Generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Regular extends Generador {

	private int gradoPretendido;

	public Regular(int cantidadNodos, int gradoPretendido) {
		super(cantidadNodos);
		this.gradoPretendido = gradoPretendido;
	}

	@Override
	public void generar() throws Exception {

		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < cantidadNodos; i++) {
			lista.add(i);
		}
		
		if(this.cantidadNodos %2 != 0 && this.gradoPretendido %2 != 0) {
			throw new Exception("No es posible, para un grafo con una cantidad de nodos impares, generar un grafo regular de grado impar.");
		}
		
		if(this.gradoPretendido >= this.cantidadNodos) {
			throw new Exception("No se puede generar un grafo regular con el grado mayor o igual a la cantidad de nodos");
		}
		
		
		
		
	}

}

package Generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Regular extends Generador {

	private int cantidad;

	public Regular(int cantidadNodos, int cantidad) {
		super(cantidadNodos);
		this.cantidad = cantidad;
	}

	@Override
	public void generar() {

		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < cantidadNodos; i++) {
			lista.add(i);
		}

		for (int i = 0; i < this.cantidadNodos; i++) {
			
			for (int j = 0; j < this.cantidad; j++) {
				int x = lista.get(j);
				if (x == i) {
					lista.get(j+1);
				}
				this.matriz.set(i, x, 1);
			}
			Collections.shuffle(lista);
		}

	}

}

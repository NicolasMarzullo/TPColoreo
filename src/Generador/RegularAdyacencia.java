package Generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegularAdyacencia extends Generador {

	private double porcentajeAdyacencia;

	public RegularAdyacencia(int cantidadNodos, int porc) {
		super(cantidadNodos);
		this.porcentajeAdy = (double) porc / 100;
	}

	@Override
	public void generar() {
		List<Integer> listJ = new ArrayList<>();

		double gradoPosible = Math.round(this.porcentajeAdy * (this.cantidadNodos - 1));
		for (int j = 0; j < this.cantidadNodos; j++) {
			listJ.add(j);
		}

		for (int i = 0; i < this.cantidadNodos; i++) {
			Collections.shuffle(listJ);
			
			for (int k = 0; k < gradoPosible; k++) {
				System.out.println(i + "  " + k);
				if (i == listJ.get(k) || this.matriz.get(i, listJ.get(k)) == 1) {
					Collections.shuffle(listJ);
					k--;
					continue;
				}

				this.matriz.set(i, listJ.get(k), 1);
			}
		}

		super.porcentajeAdy = gradoPosible / (this.cantidadNodos - 1);

	}

}

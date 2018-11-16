package Generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Porcentaje extends Generador {

	private double porcentaje;

	public Porcentaje(int cantNodos, int prob) {
		super(cantNodos);
		this.porcentaje = (double) prob / 100;
	}

	@Override
	public void generar() {
		int cantidadNecesariaAristas = (int) (this.cantidadAristasTotal() * this.porcentaje);
		List<Integer> nodos = new ArrayList<Integer>();
		int cantUniones = 0, i = 0, j, n;

		for (i = 0; i < this.cantidadNodos; i++) {
			nodos.add(i);
		}
		Collections.shuffle(nodos);

		Random rand = new Random();
		rand.setSeed(System.nanoTime());
		i = 0;

		while (cantUniones != cantidadNecesariaAristas) {
			// Random de 0 a cantidadDeNodos
			n = nodos.get(i);
			j = rand.nextInt(this.cantidadNodos - 1 + 1);

			while (n == j || this.matriz.get(i, j) == 1) {
				rand.setSeed(System.nanoTime());
				j = rand.nextInt(this.cantidadNodos - 1 + 1);
			}

			this.matriz.set(n, j, 1);
			cantUniones++;
			i++; // Uso otro nodo de la lista para unir
		}

	}
}

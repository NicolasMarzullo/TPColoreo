package Generador;

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
		int cantUniones = 0, i, j;

		Random rand = new Random(System.nanoTime());

		while (cantUniones != cantidadNecesariaAristas) {
			// Random de 0 a cantidadDeNodos
			i = rand.nextInt(this.cantidadNodos - 1 + 1);
			rand.setSeed(System.nanoTime());
			j = rand.nextInt(this.cantidadNodos - 1 + 1);

			while (i == j || this.matriz.get(i, j) == 1) {
				rand.setSeed(System.nanoTime());
				i = rand.nextInt(this.cantidadNodos - 1 + 1);
				j = rand.nextInt(this.cantidadNodos - 1 + 1);
			}

			this.matriz.set(i, j, 1);
			cantUniones++;
		}

	}
}

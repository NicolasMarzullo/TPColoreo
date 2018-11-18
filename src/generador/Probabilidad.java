package generador;

import java.util.Random;

public class Probabilidad extends Generador {

	private double prob;

	public Probabilidad(int nodos, int prob) {
		super(nodos);
		this.prob = (double) prob / 100;
	}

	@Override
	public void generar() {
		Random ran = new Random();
		for (int i = 0; i < this.cantidadNodos - 2; i++) {
			for (int j = i + 1; j < this.cantidadNodos; j++) {
				if (ran.nextFloat() <= this.prob) {
					this.matriz.set(i, j, 1);
				}
				ran.setSeed(System.nanoTime());
			}
		}
	}

}

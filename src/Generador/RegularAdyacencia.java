package Generador;

public class RegularAdyacencia extends Generador {

	public RegularAdyacencia(int cantidadNodos, double porcentajeAdyacencia) {
		super(cantidadNodos);
		this.porcentajeAdy = porcentajeAdyacencia;
	}

	@Override
	public void generar() throws Exception {

		// Formula mágica del pofe.
		double gradoPosible = Math.round((this.porcentajeAdy / 100) * (this.cantidadNodos - 1));

		int corteDeUnionesConSalteo, vueltas = 1, corteDelaCruz, j;

		if (this.cantidadNodos % 2 != 0 && gradoPosible % 2 != 0) {
			throw new Exception(
					"No es posible, para un grafo con una cantidad de nodos impares, generar un grafo regular de grado impar.");
		}

		if (gradoPosible >= this.cantidadNodos) {
			throw new Exception(
					"No se puede generar un grafo regular con el grado mayor o igual a la cantidad de nodos");
		}

		corteDeUnionesConSalteo = (int) (gradoPosible / 2);

		// El salto siempre es i+1;
		while (vueltas <= corteDeUnionesConSalteo) {
			for (int i = 0; i < this.cantidadNodos; i++) {
				j = vueltas + i;
				if (j >= this.cantidadNodos) {
					j = j % this.cantidadNodos;
				}
				this.matriz.set(i, j, 1);
			}
			vueltas++;
		}

		corteDelaCruz = this.cantidadNodos / 2;

		// Si es impar le pongo "la cruz".
		if (gradoPosible % 2 != 0) {
			for (int i = 0; i < corteDelaCruz; i++) {
				j = i + corteDelaCruz;
				this.matriz.set(i, j, 1);
			}
		}

	}

}

package Generador;

public class Regular extends Generador {

	private int gradoPretendido;

	public Regular(int cantidadNodos, int gradoPretendido) {
		super(cantidadNodos);
		this.gradoPretendido = gradoPretendido;
	}

	@Override
	public void generar() throws Exception {
		int corteDeUnionesConSalteo, vueltas = 1, corteDelaCruz, j;

		if (this.cantidadNodos % 2 != 0 && this.gradoPretendido % 2 != 0) {
			throw new Exception(
					"No es posible, para un grafo con una cantidad de nodos impares, generar un grafo regular de grado impar.");
		}

		if (this.gradoPretendido >= this.cantidadNodos) {
			throw new Exception(
					"No se puede generar un grafo regular con el grado mayor o igual a la cantidad de nodos");
		}

		corteDeUnionesConSalteo = this.gradoPretendido / 2;

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

		corteDelaCruz = this.cantidadNodos /2;
		
		if (this.gradoPretendido % 2 != 0) {
			for (int i = 0; i < corteDelaCruz; i++) {
				j = i + corteDelaCruz;
				this.matriz.set(i, j, 1);
			}
		}

	}

}

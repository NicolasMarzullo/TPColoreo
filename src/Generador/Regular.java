package Generador;

public class Regular extends Generador {

	private int gradoPretendido;

	public Regular(int cantidadNodos, int gradoPretendido) {
		super(cantidadNodos);
		this.gradoPretendido = gradoPretendido;
	}

	@Override
	public void generar() throws Exception {
		int corte, vueltas = 0, j;
		boolean impar = false;

		if (this.cantidadNodos % 2 != 0 && this.gradoPretendido % 2 != 0) {
			throw new Exception(
					"No es posible, para un grafo con una cantidad de nodos impares, generar un grafo regular de grado impar.");
		}

		if (this.gradoPretendido >= this.cantidadNodos) {
			throw new Exception(
					"No se puede generar un grafo regular con el grado mayor o igual a la cantidad de nodos");
		}

		if (this.gradoPretendido % 2 == 0) {
			corte = this.gradoPretendido / 2;
		} else {
			corte = (this.gradoPretendido / 2) - 1;
			impar = true;
		}

		// El salto siempre es i+1;
		while (vueltas < corte) {
			for (int i = 0; i < this.cantidadNodos; i++) {
				j = vueltas + i + 1;
				if (j >= this.cantidadNodos) {
					j =  j - i - vueltas - 1; //Hay que encontrarle la vuelta a esto.
				}
				this.matriz.set(i, j, 1);
			}
			vueltas++;
		}

		int corteDeLaCruz = this.cantidadNodos / 2;
		if (impar) {
			for (int i = 0; i < corteDeLaCruz; i++) {
				j = i + corteDeLaCruz;
				this.matriz.set(i, j, 1);
			}
		}

	}

}

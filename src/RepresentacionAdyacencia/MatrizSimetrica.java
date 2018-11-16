package RepresentacionAdyacencia;

import java.util.Arrays;

public class MatrizSimetrica {
	private int cantidadNodos;
	private int[][] matriz;

	public MatrizSimetrica(int cantidadNodos) {
		this.cantidadNodos = cantidadNodos;

		matriz = new int[cantidadNodos][];

		for (int i = 0; i < cantidadNodos; i++) {
			matriz[i] = new int[i];
		}
	}

	public int get(int fila, int columna) {

		if (columna == fila) {
			System.out.println("a donde vas ");
			return -1;
		}

		if (columna > fila) {
			return this.matriz[columna][fila];
		}
		return this.matriz[fila][columna];
	}

	public void set(int fila, int columna, int val) {

		if (columna == fila) {
			System.out.println("a donde vas ");
			return;
		}

		if (columna > fila) {
			this.matriz[columna][fila] = val;
			return;
		}
		this.matriz[fila][columna] = val;
	}

	public int getCantidadNodos() {
		return this.cantidadNodos;
	}

	@Override
	public String toString() {
		/*String salida = "";
		for (int i = 0; i < this.cantidadNodos; i++) {
			salida += Arrays.toString(matriz[i]) + "\n";
		}
		return salida;*/
		
		return "";
	}

}

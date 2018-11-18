package representacionAdyacencia;


public class MatrizSimetrica {
	private int cantidadNodos;
	private int[][] matriz;
	private int[] vectorGrado;

	public MatrizSimetrica(int cantidadNodos) {
		this.cantidadNodos = cantidadNodos;

		matriz = new int[cantidadNodos][];
		vectorGrado = new int[cantidadNodos];

		for (int i = 0; i < cantidadNodos; i++) {
			matriz[i] = new int[i];
		}
	}

	public int get(int fila, int columna) {

		if (columna == fila) {
			return -1;
		}

		if (columna > fila) {
			return this.matriz[columna][fila];
		}
		return this.matriz[fila][columna];
	}

	public void set(int fila, int columna, int val) {

		if (columna == fila) {
			return;
		}

		if (columna > fila) {
			this.matriz[columna][fila] = val;
			return;
		}
		this.matriz[fila][columna] = val;
		this.vectorGrado[fila]++;
		this.vectorGrado[columna]++;
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

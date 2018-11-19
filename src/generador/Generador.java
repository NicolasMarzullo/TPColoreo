package generador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

import pa.MatrizSimetrica;

public abstract class Generador {

	protected MatrizSimetrica matriz;
	protected int[] vectorGrado;
	protected int cantidadNodos;
	protected int cantidadAristas;
	protected double porcentajeAdy;
	protected int gradoMax;
	protected int gradoMin;

	public Generador(int cantidadNodos) {
		this.matriz = new MatrizSimetrica(cantidadNodos);
		this.vectorGrado = new int[cantidadNodos];
		this.cantidadNodos = cantidadNodos;
	}

	public abstract void generar() throws Exception;

	public void calcularParametrosDelGrafo() {
		int cont = 0;
		for (int i = 0; i < this.cantidadNodos; i++) {
			for (int j = 0; j < this.cantidadNodos; j++) {
				if (i != j) {
					cont += matriz.get(i, j);
				}
			}
			this.vectorGrado[i] = cont;
			cont = 0;
		}

		gradoMax = this.vectorGrado[0];
		this.gradoMin = this.vectorGrado[0];
		int sumaGrados = this.vectorGrado[0];
		for (int i = 1; i < this.cantidadNodos; i++) {
			if (this.vectorGrado[i] > gradoMax) {
				gradoMax = this.vectorGrado[i];
			}

			if (this.vectorGrado[i] < gradoMin) {
				this.gradoMin = this.vectorGrado[i];
			}
			sumaGrados += this.vectorGrado[i];
		}
		this.cantidadAristas = sumaGrados / 2;
		this.porcentajeAdy = (double) this.cantidadAristas / (this.cantidadNodos * (this.cantidadNodos - 1) / 2); // GAUS
		this.porcentajeAdy *= 100; // Lo dejo en porcentaje
	}

	public int cantidadAristasTotal() {
		return (int) (this.cantidadNodos * (this.cantidadNodos - 1) / 2);
	}

	public void escribirEnArchivo(String path) throws FileNotFoundException {
		File archivo = new File(path);
		PrintWriter salida = new PrintWriter(archivo);
		salida.print(this.cantidadNodos + " " + this.cantidadAristas + " " + this.porcentajeAdy + " " + this.gradoMax
				+ " " + this.gradoMin);
		salida.println();
		for (int i = 1; i < this.cantidadNodos; i++) {
			for (int j = 0; j < i; j++) {
				if (this.matriz.get(i, j) == 1) {
					salida.println(i + " " + j);
				}
			}
		}
		salida.close();
	}

	@Override
	public String toString() {
		return "Generador [matriz=" + matriz + ", vectorGrado=" + Arrays.toString(vectorGrado) + ", cantidadNodos="
				+ cantidadNodos + ", cantidadAristas=" + cantidadAristas + ", porcentajeAdy=" + porcentajeAdy
				+ ", gradoMax=" + gradoMax + ", gradoMin=" + gradoMin + "]";
	}

	public MatrizSimetrica getMatriz() {
		return matriz;
	}

	public int getCantidadNodos() {
		return cantidadNodos;
	}

	
	
}

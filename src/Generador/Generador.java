package Generador;

import java.util.Arrays;

import RepresentacionAdyacencia.MatrizSimetrica;

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

	public abstract void generar();

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
				System.out.println(this.vectorGrado[i]);
				this.gradoMin = this.vectorGrado[i];
			}
			sumaGrados += this.vectorGrado[i];
		}
		this.cantidadAristas = sumaGrados / 2;
		this.porcentajeAdy = (double) this.cantidadAristas / (this.cantidadNodos * (this.cantidadNodos - 1) / 2); // GAUS
	}

	public int cantidadAristasTotal() {
		return (int) (this.cantidadNodos * (this.cantidadNodos - 1) / 2);
	}

	@Override
	public String toString() {
		return "Generador [matriz=" + matriz + ", vectorGrado=" + Arrays.toString(vectorGrado) + ", cantidadNodos="
				+ cantidadNodos + ", cantidadAristas=" + cantidadAristas + ", porcentajeAdy=" + porcentajeAdy
				+ ", gradoMax=" + gradoMax + ", gradoMin=" + gradoMin + "]";
	}

}

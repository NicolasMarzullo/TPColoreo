package coloreo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import representacionAdyacencia.MatrizSimetrica;

public class GrafoNPND {
	private int cantidadDeNodos = 0;
	private int cantidadDeAristas = 0;
	private double porcentajeAdyacencia = 0;
	private int gradoMaximo = 0;
	private int gradoMinimo = 0;
	private MatrizSimetrica matrizAdyacencia;
	private List<Nodo> nodos = new ArrayList<>();
	private int cantidadDeColoresCorridaActual = 0;
	int cantidadMejorDeColores = 0;
	private int numeroDeCorridaMejorCantidadColores = 0;
	private int[] resultadoDeCorrida;
	private String nombreDeGrafo;

	public GrafoNPND(String path) throws FileNotFoundException {
		File archivo = new File(path);
		Scanner entrada = new Scanner(archivo);
		this.nombreDeGrafo = path;
		this.cantidadDeNodos = entrada.nextInt();
		this.matrizAdyacencia = new MatrizSimetrica(this.cantidadDeNodos);

		for (int i = 0; i < this.cantidadDeNodos; i++) {
			this.nodos.add(new Nodo(i));
		}

		this.cantidadDeAristas = entrada.nextInt();
		entrada.next();
		this.gradoMaximo = entrada.nextInt();
		this.gradoMinimo = entrada.nextInt();

		for (int j = 0; j < this.cantidadDeAristas; j++) {
			this.matrizAdyacencia.set(entrada.nextInt(), entrada.nextInt(), 1);
		}

		for (int k = 0; k < this.cantidadDeNodos; k++) {
			Nodo nodo = this.nodos.get(k);
			for (int j = 0; j < this.cantidadDeNodos; j++) {
				if (this.matrizAdyacencia.get(nodo.id, j) == 1) {
					nodo.grado++;
				}
			}
		}
		entrada.close();
	}

	public void colorear() {
		int nodosPintados = 0, colorActual = 1, j;
		boolean loPuedoPintarDelColorActual = true;
		Nodo nodoActual;

		List<Nodo> grafoColoreado = new ArrayList<>();
		for (int i = 0; i < this.cantidadDeNodos; i++) {
			grafoColoreado.add(new Nodo(i)); // Creo un nuevo grafo
		}

		// Uso algoritmo que colorea todo lo que puede con un color
		while (nodosPintados != this.cantidadDeNodos) {

			for (Nodo nodo : grafoColoreado) {

				if (nodo.color == 0) { // No quiero que recorra nodos que ya fueron pintados.
					j = 0;
					loPuedoPintarDelColorActual = true; // Corte de control, la primera vez siempre ingresa.
					while (j < grafoColoreado.size() && loPuedoPintarDelColorActual) {
						nodoActual = grafoColoreado.get(j);
						if (this.matrizAdyacencia.get(nodo.id, nodoActual.id) == 1) {
							if (nodoActual.color != colorActual) {
								loPuedoPintarDelColorActual = true;
							} else {
								loPuedoPintarDelColorActual = false;
							}
						}
						j++;
					}
				} else {
					loPuedoPintarDelColorActual = false;
				}

				if (loPuedoPintarDelColorActual) {
					grafoColoreado.get(nodo.id).pintar(colorActual);
					nodosPintados++;
				}
			}

			colorActual++;// Ya di una vuelta
			loPuedoPintarDelColorActual = true;
		}
		this.cantidadDeColoresCorridaActual = colorActual - 1;
	}

	public void colorearSecuencial(int cantidadDeVecesACorrer) throws FileNotFoundException {
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(nodos);
			this.colorear();
			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual - 1]++;
		}
		this.generarEstadisticas("Secuencial");
	}

	public void colorearMatula(int cantidadDeVecesACorrer) throws FileNotFoundException {
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(nodos);
			Collections.sort(nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n1.grado - n2.grado;
				}
			});
			this.colorear();

			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual]++;
		}
		this.generarEstadisticas("Matula");
	}

	public void colorearWheelsPower(int cantidadDeVecesACorrer) throws FileNotFoundException {
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(nodos);
			Collections.sort(nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n2.grado - n1.grado;
				}
			});
			this.colorear();

			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual]++;
		}
		this.generarEstadisticas("WheelsPower");
	}

	public void generarEstadisticas(String algoritmo) throws FileNotFoundException {
		File archivo = new File(this.nombreDeGrafo + " " + algoritmo);
		PrintWriter salida = new PrintWriter(archivo);
		salida.println("Mejor cantidad de Colores: " + this.cantidadMejorDeColores + "  " + "Corrida en el que salio: "
				+ this.numeroDeCorridaMejorCantidadColores);
		for (int i = 0; i < resultadoDeCorrida.length; i++) {
			salida.println("Cantidad de colores utilizados: " + (i + 1) + " " + "Cantidad de veces que se uso: "
					+ this.resultadoDeCorrida[i]);
		}
		salida.close();
	}
}

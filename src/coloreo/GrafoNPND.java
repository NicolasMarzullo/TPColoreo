package coloreo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
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
	private int[] mejorSolucion;
	private int[] nodosColoreados;

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
		entrada.useLocale(Locale.US);
		this.porcentajeAdyacencia = entrada.nextDouble();
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

//	public void colorear() {
//		int cantNodosPintados = 0, colorActual = 1, j;
//		boolean loPuedoPintarDelColorActual = true;
//
//		this.nodosColoreados = new int[this.cantidadDeNodos]; // SOLUCION
//
//		// Uso algoritmo que colorea todo lo que puede con un color
//		while (cantNodosPintados != this.cantidadDeNodos) {
//
//			for (Nodo nodo : this.nodos) {
//
//				if (this.nodosColoreados[nodo.id] == 0) { // No quiero que recorra nodos que ya fueron pintados.
//					j = 0;
//					loPuedoPintarDelColorActual = true; // Corte de control, la primera vez siempre ingresa.
//					while (j < this.nodosColoreados.length && loPuedoPintarDelColorActual) {
//						if (this.matrizAdyacencia.get(nodo.id, j) == 1) {
//							if (this.nodosColoreados[j] != colorActual) {
//								loPuedoPintarDelColorActual = true;
//							} else {
//								loPuedoPintarDelColorActual = false;
//							}
//						}
//						j++;
//					}
//				} else {
//					loPuedoPintarDelColorActual = false;
//				}
//
//				if (loPuedoPintarDelColorActual) {
//					this.nodosColoreados[nodo.id] = colorActual;
//					cantNodosPintados++;
//				}
//			}
//
//			colorActual++;// Ya di una vuelta
//			loPuedoPintarDelColorActual = true;
//		}
//		this.cantidadDeColoresCorridaActual = colorActual - 1;
//	}

	public void colorear() {
		int color = 0;
		int colorMax = 0;
		ArrayList<Nodo> grafoColoreado = new ArrayList<>();

		// grafo auxiliar el cual me encargo de pintarlo
		for (Nodo n : this.nodos) {
			grafoColoreado.add(new Nodo(n.id));
		}

		for (Nodo n : grafoColoreado) {
			color = 1;

			// Busco con qué color pintarlo
			for (int j = 0; j < this.cantidadDeNodos; j++) {
				if (this.matrizAdyacencia.get(n.id, j) == 1 && grafoColoreado.get(j).color == color
						&& grafoColoreado.get(j).color != 0) {
					color++;
				}
			}

			n.pintar(color);

			if (colorMax < color) {
				colorMax = color;
			}
		}

		this.cantidadDeColoresCorridaActual = colorMax;
	}

	public void imprimirSolucion(String algortimo) throws FileNotFoundException {
		File archivo = new File(this.nombreDeGrafo + "_Solucion_" + algortimo);
		PrintWriter salida = new PrintWriter(archivo);
		salida.println(this.cantidadDeNodos + " " + this.cantidadDeAristas);
		for (int i = 0; i < this.mejorSolucion.length; i++) {
			salida.print(this.mejorSolucion[i] + " ");
		}
		salida.println();
		salida.println();
		for (int i = 1; i < this.cantidadDeNodos; i++) {
			for (int j = 0; j < i; j++) {
				if (this.matrizAdyacencia.get(i, j) == 1) {
					salida.println(i + " " + j);
				}
			}
		}
		salida.close();
	}

	public void colorearSecuencial(int cantidadDeVecesACorrer) throws FileNotFoundException {
		this.cantidadDeColoresCorridaActual = 0;
		this.cantidadMejorDeColores = 0;
		this.numeroDeCorridaMejorCantidadColores = 0;
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
		this.cantidadDeColoresCorridaActual = 0;
		this.cantidadMejorDeColores = 0;
		this.numeroDeCorridaMejorCantidadColores = 0;
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(nodos);
			Collections.sort(nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n1.grado - n2.grado;
				}
			});

			int cotaSuperior = 0;
			int gradoActual = 0;
			int gradoAnterior = 0;
			
			for (int j = 0; j < this.nodos.size(); j++) {
				gradoAnterior = gradoActual = this.nodos.get(j).grado;
				while (gradoAnterior == gradoActual) {
					cotaSuperior++;
					if (cotaSuperior >= this.nodos.size()) {
						cotaSuperior = this.nodos.size() - 1;
						gradoActual = this.nodos.get(cotaSuperior).grado;
						break;
					}
					gradoActual = this.nodos.get(cotaSuperior).grado;
				}
				
				Collections.shuffle(this.nodos.subList(j, cotaSuperior));
				j = cotaSuperior;
			}

			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual - 1]++;
		}
		this.generarEstadisticas("Matula");
		// this.imprimirSolucion("Matula");
		// ProgramaProbadorColoreo probador = new
		// ProgramaProbadorColoreo(this.nombreDeGrafo + "_Solucion_" + "Matula");
		// probador.validarSolucion();
	}

	public void colorearWheelsPower(int cantidadDeVecesACorrer) throws FileNotFoundException {
		this.cantidadDeColoresCorridaActual = 0;
		this.cantidadMejorDeColores = 0;
		this.numeroDeCorridaMejorCantidadColores = 0;
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		this.mejorSolucion = new int[this.cantidadDeNodos];
		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(nodos);
			Collections.sort(nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n2.grado - n1.grado;
				}
			});

			int cotaSuperior = 0;
			int gradoActual = 0;
			int gradoAnterior = 0;
			
			for (int j = 0; j < this.nodos.size(); j++) {
				gradoAnterior = gradoActual = this.nodos.get(j).grado;
				while (gradoAnterior == gradoActual) {
					cotaSuperior++;
					if (cotaSuperior >= this.nodos.size()) {
						cotaSuperior = this.nodos.size() - 1;
						gradoActual = this.nodos.get(cotaSuperior).grado;
						break;
					}
					gradoActual = this.nodos.get(cotaSuperior).grado;
				}
				
				Collections.shuffle(this.nodos.subList(j, cotaSuperior));
				j = cotaSuperior;
			}

			this.colorear();

			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
				this.mejorSolucion = this.nodosColoreados;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual - 1]++;
		}
		this.generarEstadisticas("WheelsPower");
		// this.imprimirSolucion("WheelsPower");
		// ProgramaProbadorColoreo probador = new
		// ProgramaProbadorColoreo(this.nombreDeGrafo + "_Solucion_" + "WheelsPower");
		// probador.validarSolucion();
	}

	public void generarEstadisticas(String algoritmo) throws FileNotFoundException {
		File archivo = new File(this.nombreDeGrafo + " " + algoritmo);
		PrintWriter salida = new PrintWriter(archivo);
		salida.println("MejorCantidadDeColores;" + this.cantidadMejorDeColores + ";" + "CorridaEnElQueSalio;"
				+ this.numeroDeCorridaMejorCantidadColores);
		for (int i = 0; i < resultadoDeCorrida.length; i++) {
			if (this.resultadoDeCorrida[i] > 0)
				salida.println("CantidadDeColoresUtilizados;" + (i + 1) + ";" + "CantidadDeVecesQueSeUso;"
						+ this.resultadoDeCorrida[i]);
		}
		salida.close();
	}
}

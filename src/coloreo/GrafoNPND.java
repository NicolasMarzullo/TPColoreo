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
	private List<Nodo> mejorSolucion;

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

	public void colorear() {
		int color;
		int colorMax = 0;

		for (Nodo n : this.nodos) {
			n.pintar(0);// despinto todo
		}

		for (Nodo n : this.nodos) {
			color = 1;

			// Busco con qu� color pintarlo
			for (int j = 0; j < this.cantidadDeNodos; j++) {
				if (this.matrizAdyacencia.get(n.id, this.nodos.get(j).id) == 1 && this.nodos.get(j).color == color) {
					color++;
					j = -1; // Vuelvo a recorrer porque quizas este color lo tiene algun otro nodo
				}
			}

			n.pintar(color);

			if (colorMax < color) {
				colorMax = color;
			}
		}

		this.cantidadDeColoresCorridaActual = colorMax + 1;
	}

	public void imprimirSolucion(String algortimo) throws FileNotFoundException {
		File archivo = new File(this.nombreDeGrafo + "_Solucion_" + algortimo);
		PrintWriter salida = new PrintWriter(archivo);
		salida.println(this.cantidadDeNodos + " " + this.cantidadDeAristas);

		for (Nodo n : this.mejorSolucion) {
			salida.print(n.id + " " + n.color + " ");
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
		this.mejorSolucion = new ArrayList<>();

		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(this.nodos);
			this.colorear();
			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
				this.mejorSolucion.addAll(this.nodos);
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual - 1]++;
		}
		this.generarEstadisticas("Secuencial");
		// this.imprimirSolucion("Secuencial");
		ProgramaProbadorColoreo probador = new ProgramaProbadorColoreo(
				this.nombreDeGrafo + "_Solucion_" + "Secuencial");
		probador.validarSolucion();

	}

	public void colorearMatula(int cantidadDeVecesACorrer, boolean mezclar) throws FileNotFoundException {
		this.cantidadDeColoresCorridaActual = 0;
		this.cantidadMejorDeColores = 0;
		this.numeroDeCorridaMejorCantidadColores = 0;
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];

		for (int i = 0; i < cantidadDeVecesACorrer; i++) {

			Collections.sort(nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n1.grado - n2.grado;
				}
			});

			if (mezclar) {

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
			}

			this.colorear();

			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual - 1]++;
		}
		this.generarEstadisticas("Matula");
		this.imprimirSolucion("Matula");
		// ProgramaProbadorColoreo probador = new
		// ProgramaProbadorColoreo(this.nombreDeGrafo + "_Solucion_" + "Matula");
		// probador.validarSolucion();
	}

	public void colorearWheelsPower(int cantidadDeVecesACorrer, boolean mezclar) throws FileNotFoundException {
		this.cantidadDeColoresCorridaActual = 0;
		this.cantidadMejorDeColores = 0;
		this.numeroDeCorridaMejorCantidadColores = 0;
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		this.mejorSolucion = new ArrayList<>();

		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.sort(nodos, new Comparator<Nodo>() {
				@Override
				public int compare(Nodo n1, Nodo n2) {
					return n2.grado - n1.grado;
				}
			});

			if (mezclar) {
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
			}

			this.colorear();

			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
//				this.mejorSolucion = this.nodosColoreados;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual - 1]++;
		}
		this.generarEstadisticas("WheelsPower");
		// this.imprimirSolucion("WheelsPower");
		ProgramaProbadorColoreo probador = new ProgramaProbadorColoreo(
				this.nombreDeGrafo + "_Solucion_" + "WheelsPower");
		probador.validarSolucion();
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

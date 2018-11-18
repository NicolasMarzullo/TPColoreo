package coloreo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import generador.Generador;
import representacionAdyacencia.MatrizSimetrica;

public class GrafoNPND {
	private int cantidadDeNodos = 0;
	private int cantidadDeAristas = 0;
	private double porcentajeAdyacencia = 0;
	private int gradoMaximo = 0;
	private int gradoMinimo = 0;
	private MatrizSimetrica matrizAdyacencia;
	private List<Nodo> nodos;
	private int cantidadDeColoresCorridaActual = 0;
	private int cantidadMejorDeColores = 0;
	private int numeroDeCorridaMejorCantidadColores = 0;
	private int[] resultadoDeCorrida;

	public GrafoNPND(String path) throws FileNotFoundException {
		File archivo = new File(path);
		Scanner entrada = new Scanner(archivo);
		this.cantidadDeNodos = entrada.nextInt();
		this.matrizAdyacencia = new MatrizSimetrica(this.cantidadDeNodos);
		
		for (int i = 0; i < this.cantidadDeNodos; i++) {
			this.nodos.add(new Nodo(i));
		}
		
		this.cantidadDeAristas = entrada.nextInt();
		this.porcentajeAdyacencia = entrada.nextDouble();
		this.gradoMaximo = entrada.nextInt();
		this.gradoMinimo = entrada.nextInt();
		
		for (int j = 0; j < this.cantidadDeAristas; j++) {
			this.matrizAdyacencia.set(entrada.nextInt(), entrada.nextInt(), 1);
		}
		
		for(int k = 0; k < this.cantidadDeNodos;k++)
		{
			int grado = 0;
			Nodo nodo = this.nodos.get(k);
			for(int j = 0;j<this.cantidadDeNodos;j++)
			{
				if(this.matrizAdyacencia.get(nodo.id,j) == 1){
					nodo.grado++;
				}
			}
		}
		entrada.close();
	}

	public SalidaColoreo colorear() {
		return null;
	}
	
	public SalidaColoreo colorear(List<Integer> secuenciaDeRecorrido) {
		// Uso algoritmo que pinta nodo por nodo (una sola pasada).
		List<Integer> coloresUsados = new LinkedList<>();
		boolean pintar = false;
		int cantNodosPintados = 0, colorActual = 1;
		
		for (Integer nodo1 : secuenciaDeRecorrido) {
			for (Nodo nodo2 : this.nodos) {
				if (this.matrizAdyacencia.get(nodo1, nodo2.id) == 1) {
					//veo de qué color lo pinto
					
				}
				
			}
		}
		
		return new SalidaColoreo(this.nodos, 5);
	}

	public void colorearSecuencial(int cantidadDeVecesACorrer) {
		this.resultadoDeCorrida =  new int[this.cantidadDeNodos];
		for (int i = 0; i < cantidadDeVecesACorrer; i++) {
			Collections.shuffle(nodos);
			this.colorear();
			if (this.cantidadDeColoresCorridaActual < this.cantidadMejorDeColores || this.cantidadMejorDeColores == 0) {
				this.cantidadMejorDeColores = this.cantidadDeColoresCorridaActual;
				this.numeroDeCorridaMejorCantidadColores = i + 1;
			}
			this.resultadoDeCorrida[this.cantidadDeColoresCorridaActual]++;
		}
	}

	public void colorearMatula(int cantidadDeVecesACorrer) {
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		for(int i = 0;i<cantidadDeVecesACorrer;i++)
		{
			Collections.shuffle(nodos);
			Collections.sort(nodos,new Comparator<Nodo>(){
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
	}

	public void colorearWheelsPower(int cantidadDeVecesACorrer) {
		this.resultadoDeCorrida = new int[this.cantidadDeNodos];
		for(int i = 0;i<cantidadDeVecesACorrer;i++)
		{
			Collections.shuffle(nodos);
			Collections.sort(nodos,new Comparator<Nodo>(){
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
	}
}

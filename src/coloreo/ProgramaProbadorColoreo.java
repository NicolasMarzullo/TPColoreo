package coloreo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import representacionAdyacencia.MatrizSimetrica;

public class ProgramaProbadorColoreo {
	private int numeroDeNodos;
	private int numeroDeAristas;
	private MatrizSimetrica matrizDeAdyacencia;
	private int[] nodosColoreados;

	public ProgramaProbadorColoreo(String path) throws FileNotFoundException {
		File archivo = new File(path);
		Scanner entrada = new Scanner(archivo);
		this.numeroDeNodos = entrada.nextInt();
		this.numeroDeAristas = entrada.nextInt();
		this.matrizDeAdyacencia = new MatrizSimetrica(this.numeroDeNodos);
		this.nodosColoreados = new int[this.numeroDeNodos];

		for (int i = 0; i < this.numeroDeNodos; i++) {
			this.nodosColoreados[entrada.nextInt()] = entrada.nextInt();
		}

		for (int i = 0; i < this.numeroDeAristas; i++) {
			this.matrizDeAdyacencia.set(entrada.nextInt(), entrada.nextInt(), 1);
		}
		entrada.close();
	}

	public void validarSolucion() {
		for (int i = 1; i < this.numeroDeNodos; i++) {
			int color1 = this.nodosColoreados[i];
			for (int j = 0; j < i; j++) {
				int color2 = this.nodosColoreados[j];
				if (this.matrizDeAdyacencia.get(i, j) == 1 && color1 == color2) {
					System.out.println("SOLUCION MAL GENERADA");
					return;
				}
			}
		}
		System.out.println("SOLUCION CORRECTAMENTE GENERADA");
	}
}

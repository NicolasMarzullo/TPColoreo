package Generador;

import java.util.Collections;
import java.util.LinkedList;

public class Npartito extends Generador {
	private int particiones;

	public Npartito(int cantidadDeNodos, int particiones) {
		super(cantidadDeNodos);
		this.particiones = particiones;
	}

	@Override
	public void generar() throws Exception {
		LinkedList<Integer> nodos = new LinkedList<>();
		LinkedList<Integer> nodosActuales = new LinkedList<>();
		int cantNodosPorParticion, cantDeParticionesUnidas = 0, k;
		boolean hayParticionConUnoMas = false;

		cantNodosPorParticion = this.cantidadNodos / this.particiones;

		// Agrego todos los nodos
		for (int i = 0; i < this.cantidadNodos; i++) {
			nodos.add(i);
		}
		// Los mezclo
		Collections.shuffle(nodos);
		// ELIMINAR, ES PARA REVISAR QUE ANDE BIEN
		System.out.println("----LISTA----");
		for (int i = 0; i < nodos.size(); i++) {
			System.out.println(nodos.get(i));
		}

		if (this.cantidadNodos % this.particiones != 0) {
			hayParticionConUnoMas = true;
			cantNodosPorParticion++;
		}

		while (cantDeParticionesUnidas != this.particiones) {
			// Agrego los nodos actuales a una lista nueva y los saco de la otra lista
			k = 0;
			while (k < cantNodosPorParticion) {
				nodosActuales.add(nodos.getFirst());
				nodos.removeFirst();
				k++;
			}

			if (hayParticionConUnoMas) // Una sola particion va a tener uno de mas, apago la bandera
				hayParticionConUnoMas = false;

			// Uno los nodos de una particion contra todos los otros nodos
			for (int i = 0; i < nodosActuales.size(); i++) {
				for (int j = 0; j < nodos.size(); j++) {
					System.out.println("I: " + nodosActuales.get(i) + "  J:  " + nodos.get(j));
					this.matriz.set(nodosActuales.get(i), nodos.get(j), 1);
				}
			}

			// Los vuelvo a meter al final de la lista
			for (Integer i : nodosActuales) {
				nodos.addLast(i);
			}

			nodosActuales.clear();

			cantDeParticionesUnidas++;
		}

	}

}

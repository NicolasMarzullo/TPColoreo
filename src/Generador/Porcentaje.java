package Generador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Porcentaje extends Generador {

	private double porcentaje;

	public Porcentaje(int nodos, int prob) {
		super(nodos);
		this.porcentaje = (double) prob / 100;
	}

	@Override
	public void generar() {
		int cantidadNecesariaAristas = (int) (this.cantidadAristasTotal() * this.porcentaje);

		List<Integer> listaI = new ArrayList<Integer>();
		List<Integer> listaJ = new ArrayList<Integer>();
		
		for (int i = 0; i < this.cantidadNodos; i++) {
			for(int j = 0 ; j < this.cantidadNodos; j++) {
				listaI.add(i);
				listaJ.add(j);
			}
		}
		
		Collections.shuffle(listaJ);
		Collections.shuffle(listaI);
		
		for (int i = 0; i < cantidadNecesariaAristas; i++) {
			int x = listaI.get(i);
			int y = listaJ.get(i);
			if (x == y) {
				i--;
				continue;
			}
			this.matriz.set(x, y, 1);
		}
	}
}

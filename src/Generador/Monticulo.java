package Generador;

import java.util.PriorityQueue;

public class Monticulo {

	public static void main(String[] args) {
		PriorityQueue<Paciente> sala = new PriorityQueue<Paciente>();

		sala.add(new Paciente("pepe5", Prioridad.BAJA));
		sala.add(new Paciente("pepe2", Prioridad.ALTA));
		sala.add(new Paciente("pepe3", Prioridad.ALTA));
		sala.add(new Paciente("pepe1", Prioridad.URGENTE));
		sala.add(new Paciente("pepe4", Prioridad.ALTA));

		System.out.println(sala.remove());
		System.out.println(sala.remove());
		System.out.println(sala.remove());
		System.out.println(sala.remove());
		System.out.println(sala.remove());
	}

}

package Generador;

public class Paciente implements Comparable<Paciente>{

	private String nombre;
	private Prioridad prioridad;

	public Paciente(String nombre, Prioridad prioridad) {
		super();
		this.nombre = nombre;
		this.prioridad = prioridad;
	}

	
	@Override
	public String toString() {
		return "Paciente [nombre=" + nombre + ", prioridad=" + prioridad + "]";
	}

	@Override
	public int compareTo(Paciente arg0) {
		return arg0.prioridad.compareTo(this.prioridad);
	}

}

package coloreo;

public class Nodo{
	int id;
	int color;
	int grado;
	
	public Nodo(int id) {
		this.id = id;
		this.color = 0;
		this.grado = 0;
	}

	public void pintar(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Nodo [id=" + id + ", color=" + color + ", grado=" + grado + "]";
	}
	
	
	
}

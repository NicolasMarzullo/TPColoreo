package coloreo;

public class Nodo {

	int id;
	int color;
	
	
	public Nodo(int id) {
		this.id = id;
		this.color = 0;
	}
	
	public void pintar(int color) {
		this.color = color;
	}
	
	
}

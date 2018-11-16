package Generador;

public enum Prioridad {
	BAJA(1), MEDIA(2), ALTA(3), URGENTE(3);
	
	public Integer val;
	
	Prioridad(Integer v) {
		val = v;
	}
}
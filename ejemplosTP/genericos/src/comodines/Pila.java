package comodines;

class Pila<T extends Number> {
	private Object[] datos;
	private int numDatos;
	private static final int TAM_INI = 10, TAM_INC = 10;

	public Pila() {
		// no se pueden crear arrays de genericos.
		datos = new Object[TAM_INI];
		numDatos = 0;
	}

	public void push(T dato) {
		if (numDatos == datos.length) {
			Object[] aux = new Object[datos.length + TAM_INC];
			System.arraycopy(datos, 0, aux, 0, datos.length);
			datos = aux;
		}
		datos[numDatos++] = dato;
	}

	public T pop() throws Exception {
		if (numDatos > 0) {
			numDatos--;
			return (T) datos[numDatos];
		} else {
			throw new Exception("horror, pila vacia.");
		}
	}

	public boolean vacia() {
		return (numDatos == 0);
	}
}


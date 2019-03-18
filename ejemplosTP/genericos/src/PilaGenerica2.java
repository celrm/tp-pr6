public class PilaGenerica2<T extends Number> {
    private Object[] datos;
    private int numDatos;
    private static final int TAM_INI = 10, TAM_INC = 10;

    public PilaGenerica2() {
	datos = new Object[TAM_INI];
	numDatos = 0;
    }

    public void push(T dato) {
	if (numDatos == datos.length) {
	    Object[] aux = new Object[datos.length+TAM_INC];
	    System.arraycopy(datos,0,aux,0,datos.length);
	    datos = aux;
	}
	datos[numDatos++] = dato;
    }

    @SuppressWarnings("unchecked")
	public T pop() throws Exception {
	if (numDatos > 0) {
	    numDatos--;
	    return (T)datos[numDatos];
	} else { throw new Exception("horror, pila vacia."); }
    }

    public static void main(String[] args) throws Exception {
	PilaGenerica2<Number> pn = new PilaGenerica2<Number>();
	pn.push(25);
	pn.push(44);
	pn.push(37.45);
	//si intento añadir un string
	//pi.push("hola"); // esto da error de compilacion.
	Number d = pn.pop();
	System.out.println(d);
	Number i = pn.pop();
	System.out.println(i);
	
	System.out.println("------------otra pila: de enteros--------");
	PilaGenerica2<Integer> pi = new PilaGenerica2<Integer>();
	pi.push(4);
	Number e = pi.pop();
	System.out.println(e);
	
	//intentando crear una pila de string
	//PilaGenerica2<String> ps = new PilaGenerica2<String>();	
	
    }

}


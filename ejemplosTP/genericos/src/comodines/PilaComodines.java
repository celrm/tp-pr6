package comodines;


public class PilaComodines {
	public static void main(String[] args) throws Exception {
		Pila<Integer> pi = new Pila<Integer>();
		Pila<Double> pd = new Pila<Double>();
		pi.push(25);
		pi.push(44);

		// intento añadir un double a pi
		// pi.push(44.5); //da error de compilación.PROBARLO!!!!!

		escribePilaNumeros(pi);
		System.out.println("-----------------------");
		pd.push(3.5);
		pd.push(2.5);
		escribePilaNumeros(pd);
	}

	// utilizo este método para los 2 tipos de pila
	public static void escribePilaNumeros(Pila<? extends Number> p)
			throws Exception {
		// p.push(2); //esto no compila, no deja añadir aquí, el tipo es
		// desconocido.PROBARLO!!!!
		while (!p.vacia())
			System.out.println(p.pop());
	}

	// si lo hiciera sin comodín daría error de compilación en
	// escribePilaNumeros(pi) y en escribePilaNumeros(pd). PROBARLO!!!!!!!
	// Sin comodín,quedaría:
	// public static void escribePilaNumeros(Pila< Number> p) throws Exception {
	// while (!p.vacia())
	// System.out.println(p.pop());
	// }
	// Esto sí me valdría si la pila es de Number, sí compilaría

}

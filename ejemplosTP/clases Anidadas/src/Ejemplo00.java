public class Ejemplo00 {
	private static int at1 = 3;
	private int at2 = 4;

	private int m1() {
		return at1 * at2;
	}

	// esto: Interna.at4 da error pq no hay objeto creado de la clase interna
	// private int m2() {
	// return Interna.at3 * Interna.at4;
	// }

	//se resolvería así:
	private int m2() {
		Interna ci = new Interna();
		return Interna.at3 * ci.at4;
	}

	public static class Interna {
		private static int at3 = 5;
		public static int atributo = 5; //Lo hemos añadido para luego
										//probar el acceso desde otra clase
		private int at4 = 6;

		private int m3() {
			return at1 * at1;
		}
		// private int m4(){return m1()*at2;} // esto da error pq at2 no es
		// estático y m1 tampoco
	}
	

}

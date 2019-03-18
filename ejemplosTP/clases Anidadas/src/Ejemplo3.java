
public class Ejemplo3 {
	private int a1 = 1;

	public Ejemplo3(int i) {
		a1 = i;
	}

	public Interna m1() {
		Interna i = new Interna(a1 * a1);
		System.out.println("accedo al a2 de la interna desde la externa: "
				+ i.a2);
		return i;

	}

	public String toString() {
		return "a1=" + a1;
	}

	public class Interna {
		private int a2 = 6;

		public Interna(int i) {
			a2 = i;
		}

		public String toString() {
			return "a1=" + a1 + " a2=" + a2;
		}
	}

	
}

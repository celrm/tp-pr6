public class OtraClaseParaEjemplo4 {

	public static void main(String[] args) {
		Ejemplo4 x = new Ejemplo4();
		Ejemplo4.InnerClass1 a = x.new InnerClass1();
		Ejemplo4.InnerClass1.InnerClass2 b = a.new InnerClass2();
		a.test();
		b.test();
	}

}

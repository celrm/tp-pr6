
public class OtraClaseParaEjemplo3 {

	public static void main(String[] s) {
		Ejemplo3 ex1 = new Ejemplo3(2);
		Ejemplo3 ex2 = new Ejemplo3(3);
		Ejemplo3.Interna in1 = ex1.m1();
		Ejemplo3.Interna in2 = ex2.m1();
		Ejemplo3.Interna in3 = ex2.new Interna(4);
		System.out.println(in1);
		System.out.println(in2);
		System.out.println(in3);
	}

}

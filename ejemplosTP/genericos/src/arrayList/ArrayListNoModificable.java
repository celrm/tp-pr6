package arrayList;

import java.util.*;

public class ArrayListNoModificable {
	public static void main(String[] args) {
		List<Integer> arrayDeEnteros = new ArrayList<Integer>();

		arrayDeEnteros.add(1);
		arrayDeEnteros.add(2);
		arrayDeEnteros.add(3);
		System.out.println("mi arrayList: " + arrayDeEnteros);

		// hago copia de seguridad, es decir sólo lectura
		List<Integer> arrayDeEnterosRO = Collections
				.unmodifiableList(arrayDeEnteros);
		System.out.println("mi arrayList de solo lectura: " + arrayDeEnterosRO);

		// modifico mi arrayList original
		arrayDeEnteros.set(0, 4);
		System.out.println("mi arrayList tras modificarlo: " + arrayDeEnteros);

		System.out.println("mi copia de solo lectura se actualiza sola!!!!!");
		System.out.println("mi arrayList de solo lectura: " + arrayDeEnterosRO);

		// intento añadir elemento en el de sólo lectura
		 arrayDeEnterosRO.add(1); //si lo ejecutais salta la excepción

	}

}

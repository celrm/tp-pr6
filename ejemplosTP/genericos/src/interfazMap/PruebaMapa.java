package interfazMap;

import java.util.*;

public class PruebaMapa {

	public static void main(String[] args) {
		Map<String, Persona> grupo = new HashMap<String, Persona>();
		Persona p1 = new Persona("232323245M", "pepe", 37);
		Persona p2 = new Persona("525252545W", "luis", 31);
		grupo.put(p1.getDni(), p1);
		grupo.put(p2.getDni(), p2);

		// Búsqueda de un valor
		System.out
				.println("imprimimos una entrada en concreto+++++++++++++++++++");
		System.out.println(grupo.get("525252545W"));

		System.out.println("----------------------");
		System.out.println("sacando todos los pares clave-valor");
		for (Map.Entry<String, Persona> entrada : grupo.entrySet()) {
			String clave = entrada.getKey();
			Persona valor = entrada.getValue();
			System.out.println("clave= " + clave + ", valor=" + valor);
		}
		System.out.println("----------------------");

		System.out.println("sacando el conjunto de claves");
		for (String clave : grupo.keySet()) {
			System.out.println("clave= " + clave);
		}
		System.out.println("----------------------");
		System.out.println("sacando el conjunto de valores");
		for (Persona valor : grupo.values()) {
			System.out.println("valor= " + valor);
		}
		
		//voy a intentar añadir una clave que ya está
		//modifica el registro y se pierde la indormación anterior
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Persona p3 = new Persona("232323245M", "marcelo", 12);
		grupo.put(p3.getDni(), p3);
		
		System.out.println("sacando todos los pares clave-valor");
		for (Map.Entry<String, Persona> entrada : grupo.entrySet()) {
			String clave = entrada.getKey();
			Persona valor = entrada.getValue();
			System.out.println("clave= " + clave + ", valor=" + valor);
		}

	}

}

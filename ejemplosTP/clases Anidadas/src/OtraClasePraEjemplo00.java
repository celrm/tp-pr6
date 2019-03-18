
public class OtraClasePraEjemplo00 {
	// Clase que accede a la interna del Ejemplo00
	
	public static void main(String[] args) {
		
		//para acceder a un atributo estático desde otra clase
		System.out.println(Ejemplo00.Interna.atributo);
		
		//para crear un objeto de la interna desde otra clase
		Ejemplo00.Interna i = new Ejemplo00.Interna();
		
		

	}

}
public class EjemploVolatil2 {

	// Ejemplo del uso de volatile para que todas las hebras tengan la ultima
	// actualización de una variable compartida. Si la var. contador no es
	// volatile, el programa puede no terminar porque las hebras pueden no tener
	// la última actualización.
	// Si la variable es volatile, el programa siempre termina porque se
	// propaga el ultimo cambio a todas las hebras.

	// Probarlo con volatile y sin volatile.
	// Comprobar que no termina porque tras hacer el hilo 2 el primer cambio,
	// el hilo 1 no vuelve a coger el valor actualizado de la variable contador
	// .

	
	//Preguntas: donde hay espera activa?
	//Y si pongo un System.out.println en el hilo 1 como primera linea del while
	//y lo ejecuto sin volatile
	
	private  static int contador = 0;

	private static class Hilo1 extends Thread {
		@Override
		public void run() {
			int local = contador;
			while (local < 5) {
				
				// Local será distindo de contador si el otro hilo hizo un
				// cambio.
				// Si son iguales el bucle no hace nada.
				// El otro hilo modifica contador y cuando eso ocurre entro en
				// el if
				if (local != contador) {
					System.out.println("Ha habido un cambio!!: " + contador);
					// Con la siguiente linea "local" y "contador" vuelven a
					// tomar el mismo valor
					// hasta que el otro hilo vuelva a cambiar "contador"
					local = contador;
				}
			}
			System.out.println("Termina el hilo 1");
		}
	}

	// este hilo modifica la variable contador
	private static class Hilo2 extends Thread {
		@Override
		public void run() {

			int local = contador;
			while (local < 5) {
				System.out.println("Vamos a incrementar a " + (local + 1));
				// incrementamos contador en 1 cada 1000 ms
				contador = ++local;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Termina el hilo 2");
		}
	}

	public static void main(String[] args) {
		new Hilo1().start();
		new Hilo2().start();
	}
}

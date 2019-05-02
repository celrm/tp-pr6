// Ejemplo mínimo de hebras, sin swing.
// Uso de la herencia de Thread y del método start().
// REALIZAR MUCHAS EJECUCIONES Y OBSERVAR QUE CADA VEZ SALE EN 
// DISTINTO ORDEN

public class Ejemplo1 {
	private static class MiThread extends Thread {
		private int num;

		public MiThread(int constante) {
			this.num = constante;
		}

		public void run() {
			int i;
			for (i = 0; i < 10; i++) {
				System.out.print("estoy en la hebra: " + num
						+ System.getProperty("line.separator"));

			}
		}
	}

	public static void main(String[] args) {

		MiThread th0 = new MiThread(0);
		//th0.run(); //así sería para ejecutarlo en la hebra ppal
					//Observar que de esta manera siempre se hará 
					//primero las del run y después las del for
		
		th0.start(); // Ejecución CONCURRENTE 
		int i;
		for (i = 0; i < 10; i++) {
			System.out.print("estoy en el main "
					+ System.getProperty("line.separator"));

		}
	}
}

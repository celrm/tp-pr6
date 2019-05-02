// Ejemplo mínimo de hebras, sin swing.

public class Ejemplo1c {
	private static class MiThread extends Thread {
		private int num;

		public MiThread(int constante) {
			this.num = constante;
		}

		// este método llama a otro de la misma clase,
		// no hay problema de compartición de datos pues
		// cada hebra tiene sus datos.

		public void run() {
			mirun(num);
		}

		public void mirun(int n) {

			int i;
			for (i = 0; i < 10; i++) {
				System.out.print("estoy en la hebra: " + n
						+ System.getProperty("line.separator"));
			}
		}

	}

	public static void main(String[] args) {
		MiThread th0 = new MiThread(0);

		// vamos a acceder a la misma variable num desde 2 hilos.

		th0.start(); // Ejecución CONCURRENTE
		th0.mirun(2);
		// llamada a un método de un Thread desde otro hilo (el del main)
		// No tenemos problemas de compartición de datos pues num
		// es de sólo lectura,
		// si num fuera de lectura y escritura sí tendríamos problemas
		// de compartición de datos
		System.out.println("terminó el main!!!!!!!!!!!!!!!");
	}
}

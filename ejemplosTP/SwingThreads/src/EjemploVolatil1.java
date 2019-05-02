public class EjemploVolatil1 {

	// observar que con volatile si termina el bucle y sin volatile no

	 static boolean f = false;

	public static void main(String[] args) throws InterruptedException {
		
		new Thread() {

			public void run() {
				
				System.out.println("comienza la ejecución del hilo...");
				while (!f) {
					
					//probarlo con esto y sin esto
					System.out.println("aqui� , pasando el rato");
				}
				
				System.out.println("La hebra ha terminado!!!!!!!!!!!!!!!!!!!!!!");	
				
			};
		}.start();

		Thread.sleep(2000);
		System.out.println("poniendo f a 'true'");
		f = true;
		System.out.println("El main termina");

	}

}

public class SeTerminaAsiMismo {

	public static void main(String[] args) {
		Thread th1 = new Thread(new Runnable() {

			public void run() {
				while (true && !Thread.currentThread().isInterrupted()) {
					// comprobar que sin la condici�n
					// !Thread.currentThread().isInterrupted()
					// no termina
					System.out.println("HOLA");
				}
			}
		});
		th1.start();
		System.out.println("el main se va a dormir");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			return;
		}

		th1.interrupt();
		System.out.println("terminando el main");

	}

}

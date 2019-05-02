// Ejemplo de programación "thread-UNsafe"
// Simulación de contabilidad de clientes de una tienda online. 
// El total de la contabilidad y el total por clientes debería coincidir siempre. 
// Si se ejecutan secuencialmente, coinciden los totales.
// ¿Qué ocurre si se ejecutan concurrentemente?
import java.util.Random;

public class EjemploUnsafe {
	public static final int NUM = 1000;

	static class ItemImporte {
		private int importe;
		private ItemImporte next;

		public ItemImporte(int imp) {
			importe = imp;
			next = null;
		}
	}

	static class Contabilidad {
		// lleva una lista enlazada de importes
		// para ello llevar una referencia al primero y al último.
		private ItemImporte primero = null;
		private ItemImporte ultimo = null;

		public synchronized void add(int importe) {
			if (primero == null) {// no tenemos nigún impote todavía
				primero = new ItemImporte(importe);
				ultimo = primero;

			} else {
				ItemImporte nuevo = new ItemImporte(importe);
				ultimo.next = nuevo;// enlazo
				ultimo = nuevo;// actualizo el ultimo
			}
		}

		// sumo todos los importes de la lista enlazada y acumulo el total
		public int calcTotal() {
			int total = 0;
			ItemImporte item = primero;
			while (item != null) {
				total = total + item.importe;
				item = item.next;
			}
			return total;
		}
	}

	static class ClienteOnline extends Thread {
		private String id;
		private Contabilidad ct;
		private int acumulado = 0;// inicialmente cada cliente ha gastado 0
									// euros

		public ClienteOnline(String id, Contabilidad s) {
			this.id = id;
			ct = s;
		}

		public void run() {
			Random rnd = new Random();
			for (int i = 0; i < NUM; i++) {
				int importe = rnd.nextInt(100);
				// genero el importe de cada compra de manera aleatoria,
				// cada compra se guarda en la lista enlazada de la clase
				// contabilidad,
				ct.add(importe);// recurso no compatible por ser de
								// lect/escritura
				// cada compra la guardo también en la variable "acumulado" de
				// cada cliente
				acumulado = acumulado + importe;
				// la variable acumuladdo no da problemas, cada hebra tiene la
				// suya.
			}
		}

		public int getAcumulado() {
			return acumulado;
		}
	}

	// Realizar distintas ejecuciones:
	// 1. clientes con run() y sin synchronized => sale correcta la contabilidad
	// 2. clientes con start() y sin synchronized => contabilidad incorrecta
	// 3. clientes con start() y con synchronized en el método add =>
	// => contabilidad correcta
	
	//Problema: los accesos al recurso no compartible(la lista enlazada)
	//por 2 hebras distintas sin sincronización.
	
	//Solución: que el método add sea atómico, para ello utilizar la palabra
	//reservada synchronized

	public static void main(String[] args) throws InterruptedException {
		Contabilidad contab = new Contabilidad();
		// a los dos clientes les paso el mismo objeto contabilidad
		ClienteOnline cli1 = new ClienteOnline("cliente 1", contab);
		ClienteOnline cli2 = new ClienteOnline("cliente 2", contab);
		cli1.start();
		cli2.start();
		// cli1.run();
		// cli2.run();
		// el main espera a que terminen de comprar los 2 clientes
		cli1.join();
		cli2.join();
		int totalPorClientes = cli1.getAcumulado() + cli2.getAcumulado();
		// El totalPorClientes debería ser igual al total del objeto
		// contabilidad
		System.out.println("Total por clientes   : " + totalPorClientes);
		System.out.println("Total en contabilidad: " + contab.calcTotal());
	}
}

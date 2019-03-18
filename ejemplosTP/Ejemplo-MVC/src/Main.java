import javax.swing.SwingUtilities;

// Ejemplo mínimo de MVC.

public class Main {

	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Controlador control = new Controlador(modelo);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Vista(control);
			}
		});

	}

}

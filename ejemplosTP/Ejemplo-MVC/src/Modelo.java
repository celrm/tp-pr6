import java.util.ArrayList;

public class Modelo {
	// En observadores llevaremos las vistas registradas como observadores del
	// modelo
	ArrayList<ObservadorModelo> observadores;
	double res;

	public Modelo() {
		observadores = new ArrayList<ObservadorModelo>();
	}

	public void calcular(double valor) {
		res = valor * valor;
		notifyUpdate(res);// notifica a las vistas
	}

	private void notifyUpdate(Double dato) {
		for (ObservadorModelo ob : observadores) {
			ob.actualizar(dato);
		}
	}

	// añade la vista al arrayList observadores
	public void addObservador(ObservadorModelo ob) {
		observadores.add(ob);
	}

	public void removeObservador(ObservadorModelo ob) {
		observadores.remove(ob);
	}
}

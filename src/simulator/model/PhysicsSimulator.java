package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {
	private double tRealPaso;
	private GravityLaws ley;
	private double time;
	private List<Body> cuerpos;
	public PhysicsSimulator(double t_real, GravityLaws leyes) throws IllegalArgumentException{
		if (t_real > 0.0 && leyes != null){
			tRealPaso = t_real;
			ley = leyes;
			time = 0.0;
			cuerpos = new ArrayList<>(); // vale no se usar listas
		}
		else throw new IllegalArgumentException();
	}
}

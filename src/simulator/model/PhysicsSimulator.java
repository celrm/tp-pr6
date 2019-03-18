package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;

public class PhysicsSimulator {
	private double dt;
	private GravityLaws ley;
	
	private double time;
	private ArrayList<Body> cuerpos;
	private ArrayList<SimulatorObserver> observers;
	
	public PhysicsSimulator(double t_real, GravityLaws leyes) throws IllegalArgumentException {
		if (t_real <= 0.0) throw new IllegalArgumentException("tiempo no valido");
		if (leyes == null) throw new IllegalArgumentException("ley no valida");
		
		dt = t_real;
		ley = leyes;		

		time = 0.0;
		cuerpos = new ArrayList<Body>();
		observers = new ArrayList<SimulatorObserver>();
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!observers.contains(o)) observers.add(o);
		o.onRegister(cuerpos, time, dt, ley.toString());
	}
	
	public void advance() {
		ley.apply(cuerpos);
		for (Body body : cuerpos) {
			body.move(dt);
		}
		time += dt;
		
		for(SimulatorObserver o : observers) {
			o.onAdvance(cuerpos, time);
		}
	}
	public void addBody(Body b) throws IllegalArgumentException {
		boolean unico = true;
		for (Body body : cuerpos) {
			unico = unico && !(body.equals(b));
		}
		if(!unico) throw new IllegalArgumentException("Body " + b.id + " already exists");
		cuerpos.add(b);
		
		for(SimulatorObserver o : observers) {
			o.onBodyAdded(cuerpos, b);
		}
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{ \"time\": ");
		s.append(time);
		s.append("\n\t, \"bodies\": \n\t\t[ ");
		Iterator<Body> iter = cuerpos.iterator();
		if(!cuerpos.isEmpty())	// separo el primero para la ,
			s.append(iter.next());
	    while (iter.hasNext()) {
	    	s.append("\n\t\t, ");
	    	s.append(iter.next());
	    }
		s.append("\n\t\t]\n\t}");
		return s.toString();
	}
	public void reset() {
		cuerpos = new ArrayList<Body>();
		time = 0.0;
		
		for(SimulatorObserver o : observers) {
			o.onReset(cuerpos, time, dt, ley.toString());
		}
	}
	public void setDeltaTime(double tt) throws IllegalArgumentException {
		if (tt <= 0.0) throw new IllegalArgumentException("dt no valido"); // mas preguntas
		dt = tt;
		
		for(SimulatorObserver o : observers) {
			o.onDeltaTimeChanged(tt);
		}
	}
	public void setGravityLaws(GravityLaws gl) throws IllegalArgumentException {
		if (gl == null) throw new IllegalArgumentException("ley no valida");
		ley = gl;
		
		for(SimulatorObserver o : observers) {
			o.onGravityLawChanged(gl.toString());
		}
	}
}

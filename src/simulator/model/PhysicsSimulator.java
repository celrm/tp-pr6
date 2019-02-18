package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;

public class PhysicsSimulator {
	private double dt;
	private GravityLaws ley;
	
	private double time;
	private ArrayList<Body> cuerpos;
	
	public PhysicsSimulator(double t_real, GravityLaws leyes) throws IllegalArgumentException {
		if (t_real <= 0.0) throw new IllegalArgumentException("tiempo no valido");
		if (leyes == null) throw new IllegalArgumentException("ley no valida");
		
		dt = t_real;
		ley = leyes;		

		time = 0.0;
		cuerpos = new ArrayList<Body>();
	}
	public void advance() {
		ley.apply(cuerpos);
		for (Body body : cuerpos) {
			body.move(dt);
		}
		time += dt;
	}
	public void addBody(Body b) throws IllegalArgumentException {
		boolean unico = true;
		for (Body body : cuerpos) {
			unico = unico && !(body.id.equals(b.id));
		}
		if(!unico) throw new IllegalArgumentException("Body " + b.id + " already exists");
		cuerpos.add(b);
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
//	public JSONObject toObject() {
//		JSONObject s = new JSONObject();
//		s.put("time", time);
//		JSONArray bods = new JSONArray();
//		Iterator<Body> iter = cuerpos.iterator();
//		while (iter.hasNext()) {
//		 bods.put(iter.next().toObject());
//	    }
//		s.put("bodies", bods);
//		return s;
//	}
}

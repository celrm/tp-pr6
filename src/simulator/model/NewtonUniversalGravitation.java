package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {
	
	public static final double G = 6.67E-11;
	
	public NewtonUniversalGravitation() {}

	@Override
	public void apply(List<Body> bodies) { 
		for(Body bi : bodies) {			
			bi.setAcceleration(new Vector(bi.getAcceleration().dim())); // ai = 0
			
			if(bi.getMass() == 0.0)
				bi.setVelocity(new Vector(bi.getVelocity().dim())); // vi = 0
			
			else for(Body bj : bodies) {				
				if(bi != bj) { // por referencia
					Vector pj = bj.getPosition();
					
					double r = pj.distanceTo(bi.getPosition()); // |pj-pi|
					double fij = G*bj.getMass()/(r*r); // G*mj/r^2
					Vector aux = pj.minus(bi.getPosition()).direction().scale(fij);
					bi.setAcceleration(bi.getAcceleration().plus(aux));
				}
			}
		}
	}

	public String toString() {
		return "Newton's law of universal gravitation (nlug)";
	}
}

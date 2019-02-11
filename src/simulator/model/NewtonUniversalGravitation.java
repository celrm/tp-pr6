package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {
	
	public static final double G = 6.67E-11;
	
	public NewtonUniversalGravitation() {}

	@Override
	public void apply(List<Body> bodies) {
		for(Body bi : bodies) {
			Vector pi = bi.getPosition();
			Vector vi = bi.getVelocity();
			Vector ai = bi.getAcceleration();
			
			bi.setAcceleration(new Vector(ai.dim())); // ai = 0
			
			if(bi.getMass() == 0.0)
				bi.setVelocity(new Vector(vi.dim())); // vi = 0
			
			else for(Body bj : bodies) {				
				if(bi != bj) { // por referencia
					Vector pj = bj.getPosition();
					
					double r = pj.distanceTo(pi); // |pj-pi|
					double fij = G*bj.getMass()/(r*r); // G*mj/r^2
					Vector aux = pj.minus(pi).direction().scale(fij);
					bi.setAcceleration(ai.plus(aux));
				}
			}
		}
		
	}
}

package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {
	
	public NewtonUniversalGravitation() {
	
	}

	@Override
	public void apply(List<Body> bodies) {
		for(Body bi : bodies) {
			if(bi.getMass() == 0.0) {
				bi.setVelocity(new Vector(bi.getVelocity().dim()));
				bi.setAcceleration(new Vector(bi.getAcceleration().dim()));
			}
			else {
				for(Body bj : bodies) {
					if(bi != bj) {
						double rij = bi.getPosition().distanceTo(bj.getPosition()); // |pj-pi|
						double fij = (6.67E-11)*bi.getMass()*bj.getMass()/(rij*rij); // G*mj/r^2
						Vector aux = bj.getPosition().minus(bi.getPosition()).direction().scale(fij);
						bi.setAcceleration(aux);
					}
				}
			}
		}
		
	}
}

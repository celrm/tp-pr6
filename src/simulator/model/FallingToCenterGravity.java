package simulator.model;

import java.util.List;

public class FallingToCenterGravity implements GravityLaws {
	
	public FallingToCenterGravity() {}

	@Override
	public void apply(List<Body> bodies) {
		double g = 9.81;
		for(Body bi : bodies) {
			bi.setAcceleration(bi.getPosition().direction().scale(-g));
		}
	}
	
	public String toString() {
		return "Falling to center Gravity (ftcg)";
	}
}

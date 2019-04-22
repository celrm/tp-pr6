package simulator.model;

import simulator.misc.Vector;

public class MassLosingBody extends Body {
	private double lossFactor;
	private double lossFrequency;
	
	private double c = 0.0;
	
	public MassLosingBody(String id, Vector v, Vector a, Vector p, double m, double lfa, double lfr) {
		super(id,v,a,p,m);
		lossFactor = lfa; 
		lossFrequency = lfr;
	}
	
	void move(double t) {
		super.move(t);
		c += t;
		
		if(c >= lossFrequency) {
			c = 0.0;
			m = m*(1-lossFactor);
		}
	}
}

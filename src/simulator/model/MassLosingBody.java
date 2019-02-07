package simulator.model;

import simulator.misc.Vector;

public class MassLosingBody extends Body {
	private double lossFactor;
	private double lossFrequency;
	
	private double c = 0.0;
	
	public MassLosingBody(String id, Vector v, Vector a, Vector p, double m, double lfa, double lfr) {
		super(id,v,a,p,m);
		lossFactor = lfa; //a lo mejor hay que lanzar excepción si no es 0 < lfa < 1
		lossFrequency = lfr; //y lo mismo con esto que sea positivo(increible explicacion maría muy necesario)
	} // TODO constructora
	
	void move(double t) {
		super.move(t);
		c += t;
		
		if(c >= lossFrequency) {
			c = 0.0;
			m = m*(1-lossFactor);
		}

//		while(c >= lossFrequency) {
//			c -= lossFrequency;
//			m = m*(1-lossFactor);
//		}
	} // TODO convencer de que está mal
}

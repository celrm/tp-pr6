package simulator.model;

public class MassLosingBody extends Body {
	private double lossFactor;
	private double lossFrequency;
	
	private double c = 0.0;
	
	public MassLosingBody(double lfa, double lfr) {
		lossFactor = lfa;
		lossFrequency = lfr;
	} // TODO constructora
	
	void move(double t) {
		super.move(t);
		c += t;
		while(c >= lossFrequency) {
			c -= lossFrequency;
			super.setMass(getMass()*(1-lossFactor));
		}
	}
}

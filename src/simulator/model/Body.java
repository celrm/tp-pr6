package simulator.model;

import simulator.misc.Vector;

public class Body {
	protected String id;
	protected Vector v;
	protected Vector a;
	protected Vector p;
	protected double m;
	
	public Body(String id, Vector v, Vector a, Vector p, double m) {
		this.id = id; //inmutable no ?
		this.v = new Vector(v);
		this.a = new Vector(a);
		this. p = new Vector(p);
		this.m = m;
	} 

	public String getId() {
		return id;
	} // devuelve el identificador del cuerpo.
	
	public Vector getVelocity() {
		return new Vector(v);
	} // devuelve una copia del vector de velocidad.
	
	public Vector getAcceleration() {
		return new Vector(a);
	} // devuelve una copia del vector de aceleración.
	
	public Vector getPosition() {
		return new Vector(p);
	} // devuelve una copia del vector de posición.
	
	double getMass() {
		return m;
	} // devuelve la masa del cuerpo.
	
	void setVelocity(Vector v) {
		this.v = new Vector(v);
	} // hace una copia de v y se la asigna al vector de velocidad.
	
	void setAcceleration(Vector a) {
		this.a = new Vector (a);
	} // hace una copia de a y se la asigna al vector de aceleración.
	
	void setPosition(Vector p) {
		this.p = new Vector(p);
	} // hace una copia de p y se la asigna al vector de posición.
	
	void move(double t) {
		p = p.plus(v.scale(t).plus(a.scale(t*t/2.0)));
		v = v.plus(a.scale(t));
	} // mueve el cuerpo durante t segundos utilizando los atributos del mismo. 

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("{ \"id\": \"");
		s.append(id);
		s.append("\"\n\t\t, \"mass\": ");
		s.append(m);
		s.append("\n\t\t, \"pos\": ");
		s.append(p);
		s.append("\n\t\t, \"vel\": ");
		s.append(v);
		s.append("\n\t\t, \"acc\": ");
		s.append(a);
		s.append("\n\t\t}");
		return s.toString();
	}
//	public JSONObject toObject() {
//		JSONObject s = new JSONObject();
//		s.put("id", id);
//		s.put("mass", m);
//		s.put("pos", p);
//		s.put("vel", v);
//		s.put("acc", a);
//		return s;
//	}
}

package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller {
	private PhysicsSimulator simulador;
	private Factory<Body> fb;
	private Factory<GravityLaws> fgl;

	public Controller(PhysicsSimulator sim, Factory<Body> factb, Factory<GravityLaws> factgl){
		simulador = sim;
		fb = factb;
		fgl = factgl;
	}
	public void loadBodies(InputStream in){
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray array = jsonInput.getJSONArray("bodies");
		for (int i = 0; i < array.length(); i++)
			simulador.addBody(fb.createInstance(array.getJSONObject(i)));
	}

	public void run(int n){
		for (int i= 0; i < n;++i){
			simulador.advance();
		}
	}
	
	public void run(int n, OutputStream out){
		StringBuilder s = new StringBuilder();
		s.append("{ \"states\": \n[ ");
		
		if(n>0)
			s.append(simulador);
		for (int i= 0; i < n;++i){
			simulador.advance();
	    	s.append("\n, ");
			s.append(simulador);
		}	    
		s.append("\n] }");
		PrintStream p = (out==null) ? null :new PrintStream(out);
		p.println(s);
		p.close();
	}
	public void reset() {
		simulador.reset();
	}
	public void setDeltaTime(double dt) {
		simulador.setDeltaTime(dt);
	}
	public void addObserver(SimulatorObserver o) {
		simulador.addObserver(o);
	}
	public Factory<GravityLaws> getGravityLawsFactory() {
		return fgl;
	}
	public void setGravityLaws(JSONObject info) {
		GravityLaws gravedad = fgl.createInstance(info);
		simulador.setGravityLaws(gravedad);
	}
	
}

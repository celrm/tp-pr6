package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	private PhysicsSimulator simulador;
	private Factory<Body> factoria;
	public Controller(PhysicsSimulator sim, Factory<Body> fact){
		simulador = sim;
		factoria = fact;
	}
	public void loadBodies(InputStream in){
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray array = jsonInput.getJSONArray("bodies");
		try{
		for (int i = 0; i < array.length(); i++) {
			simulador.addBody(factoria.createInstance(array.getJSONObject(i)));
		}
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	public void run(int n, OutputStream out){
		JSONObject estados = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i= 0; i < n;++i){
			simulador.advance();
			array.put(simulador.toString());
		}
		estados.put("states", array);
		System.out.println(estados);
	}
	
}

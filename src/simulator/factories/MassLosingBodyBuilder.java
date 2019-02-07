package simulator.factories;
import org.json.JSONObject;

import simulator.model.Body;

public class MassLosingBodyBuilder extends Builder<Body> {

	public MassLosingBodyBuilder(){
		typeTag = "mlb";
		desc = "Mass Losing Body";
	}
	@Override
	public Body createInstance(JSONObject info) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getBulilderInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

package simulator.factories;
import org.json.JSONObject;

import simulator.model.GravityLaws;
public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {

	public NewtonUniversalGravitationBuilder(){
		typeTag = "nlug";
		desc = "Newton's law of universal gravitation";
	}
	@Override
	public GravityLaws createInstance(JSONObject info)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getBulilderInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

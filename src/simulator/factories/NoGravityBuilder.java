package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
public class NoGravityBuilder extends Builder<GravityLaws>{
	
	public NoGravityBuilder(){
		typeTag = "ng";
		desc = "No gravity";
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

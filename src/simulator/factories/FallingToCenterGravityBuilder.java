package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
public class FallingToCenterGravityBuilder extends Builder<GravityLaws>{

	public FallingToCenterGravityBuilder(){
		typeTag = "ftcg";
		desc = "Falling to Center Gravity";
	}
	@Override
	public GravityLaws createInstance(JSONObject info)
			throws IllegalArgumentException {
		
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

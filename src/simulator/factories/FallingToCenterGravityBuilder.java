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
	public JSONObject getBuilderInfo() {
		JSONObject sol = new JSONObject();
		sol.put("type", typeTag);
		sol.put("data", "empty");
		return sol;
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
public class NoGravityBuilder extends Builder<GravityLaws>{
	
	public NoGravityBuilder(){
		typeTag = "ng";
		desc = "No gravity";
	}


	@Override
	public JSONObject getBuilderInfo() {
		JSONObject sol = new JSONObject();
		sol.put("type", typeTag);
		JSONObject otro = new JSONObject();
		sol.put("data", otro);
		return sol;
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

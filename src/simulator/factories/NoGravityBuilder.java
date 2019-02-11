package simulator.factories;

import org.json.JSONObject;
import simulator.model.GravityLaws;
import simulator.model.NoGravity;

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
		sol.put("desc", desc);
		return sol;
	}

	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		return new NoGravity();
	}

}

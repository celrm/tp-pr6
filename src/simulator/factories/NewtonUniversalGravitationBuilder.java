package simulator.factories;
import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;
public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws> {

	public NewtonUniversalGravitationBuilder(){
		typeTag = "nlug";
		desc = "Newton's law of universal gravitation";
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
		return new NewtonUniversalGravitation();
	}

}

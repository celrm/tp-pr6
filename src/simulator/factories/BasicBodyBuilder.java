package simulator.factories;

import org.json.JSONObject;

import simulator.model.Body;

public class BasicBodyBuilder extends Builder <Body>{
	
	public BasicBodyBuilder(){
		typeTag = "basic";
		desc = "Basic body";
	}

	@Override
	public JSONObject getBuilderInfo() {
		JSONObject sol = new JSONObject();
		sol.put("type", typeTag);
		JSONObject otro = new JSONObject();
		otro.put("id", "identificador");
		otro.put("pos", "posicion");
		otro.put("vel", "velocidad");
		otro.put("mass", "masa");
		sol.put("data", otro);
		return sol;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

package simulator.factories;
import org.json.JSONObject;

import simulator.model.Body;

public class MassLosingBodyBuilder extends Builder<Body> {

	public MassLosingBodyBuilder(){
		typeTag = "mlb";
		desc = "Mass Losing Body";
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
		otro.put("freq", "frecuencia");
		otro.put("factor", "factor");
		sol.put("data", otro);
		return sol;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

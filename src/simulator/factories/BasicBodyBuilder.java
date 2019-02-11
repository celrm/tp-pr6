package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
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
		JSONObject data = new JSONObject();
		data.put("id", "identificador");
		data.put("pos", "posicion");
		data.put("vel", "velocidad");
		data.put("mass", "masa");
		sol.put("data", data);
		sol.put("desc", desc);
		return sol;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) throws IllegalArgumentException {
		try {
			String id =jsonObject.getString("id");
			Double m = jsonObject.getDouble("mass");			

			Vector a = new Vector(2);
			double[] arr = new double[a.dim()];
			
			JSONArray vel = jsonObject.getJSONArray("vel");
			if (vel.length()!=a.dim())
				throw new IllegalArgumentException("wrong v dim");
			for(int i = 0; i < a.dim(); ++i)
				arr[i] = vel.getDouble(i);
			Vector v = new Vector(arr);
			
			JSONArray pos = jsonObject.getJSONArray("pos");
			if (pos.length()!=a.dim())
				throw new IllegalArgumentException("wrong p dim");
			for(int i = 0; i < a.dim(); ++i)
				arr[i] = pos.getDouble(i);
			Vector p = new Vector(arr);

			return new Body(id, v, a, p, m);
		}
		catch(JSONException e) {
			throw new IllegalArgumentException("Error while instancing BB: " + e.getMessage());
		}
	}
}

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
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("id", "identificador");
		data.put("pos", "posicion");
		data.put("vel", "velocidad");
		data.put("mass", "masa");
		return data;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) throws IllegalArgumentException {
		try {
			String id =jsonObject.getString("id");
			Double m = jsonObject.getDouble("mass");			

			Vector a = new Vector(2);
			
			JSONArray vel = jsonObject.getJSONArray("vel");
			if (vel.length()!=a.dim())
				throw new IllegalArgumentException("wrong v dim");			
			Vector v = new Vector(jsonArrayTodoubleArray(vel));			
			
			JSONArray pos = jsonObject.getJSONArray("pos");
			if (pos.length()!=a.dim())
				throw new IllegalArgumentException("wrong p dim");			
			Vector p = new Vector(jsonArrayTodoubleArray(pos));

			return new Body(id, v, a, p, m);
		}
		catch(JSONException e) {
			throw new IllegalArgumentException("Error while instancing BB: " + e.getMessage());
		}
	}

}

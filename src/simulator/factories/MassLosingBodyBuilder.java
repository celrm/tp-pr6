package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.MassLosingBody;
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
		sol.put("desc", desc);
		return sol;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		try {
			String id =jsonObject.getString("id");
			Double m = jsonObject.getDouble("mass");
			Double lfa = jsonObject.getDouble("factor");
			Double lfr = jsonObject.getDouble("freq");

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

			return new MassLosingBody(id, v, a, p, m, lfa, lfr);
		}
		catch(JSONException e) {
			throw new IllegalArgumentException("error while instancing: " + e.getMessage());
		}
	}

}

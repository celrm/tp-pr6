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
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("id", "identificador");
		data.put("pos", "posicion");
		data.put("vel", "velocidad");
		data.put("mass", "masa");
		data.put("freq", "frecuencia");
		data.put("factor", "factor");
		return data;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		try {
			String id =jsonObject.getString("id");
			Double m = jsonObject.getDouble("mass");
			
			Double lfa = jsonObject.getDouble("factor");
			if (0>=lfa || lfa >= 1)
				throw new IllegalArgumentException("wrong lossFactor");
			
			Double lfr = jsonObject.getDouble("freq");
			if (0>=lfr)
				throw new IllegalArgumentException("wrong lossFrequency");

			Vector a = new Vector(2);
			
			JSONArray vel = jsonObject.getJSONArray("vel");
			if (vel.length()!=a.dim())
				throw new IllegalArgumentException("wrong v dim");			
			Vector v = new Vector(jsonArrayTodoubleArray(vel));			
			
			JSONArray pos = jsonObject.getJSONArray("pos");
			if (pos.length()!=a.dim())
				throw new IllegalArgumentException("wrong p dim");			
			Vector p = new Vector(jsonArrayTodoubleArray(pos));			

			return new MassLosingBody(id, v, a, p, m, lfa, lfr);
		}
		catch(JSONException e) {
			throw new IllegalArgumentException("Error while instancing MLB: " + e.getMessage());
		}
	}

}

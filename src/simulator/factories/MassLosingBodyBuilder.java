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
		double[] arr = {(0.0),(0.0)};
		Vector a = new Vector(arr);
		String id;
		Double m;
		Double lfa;
		Double lfr;
		Vector v = new Vector(a);
		Vector p = new Vector(a);
		try{
			id =jsonObject.getString("id");
			m = jsonObject.getDouble("mass");
			lfa = jsonObject.getDouble("factor");
			lfr = jsonObject.getDouble("freq");
			JSONArray vel = jsonObject.getJSONArray("vel");
			if (vel.length()!=2){
				throw  new IllegalArgumentException();
			}
			arr[0] =vel.getDouble(0);
			arr[1] = vel.getDouble(1);
			v = new Vector(arr);
			JSONArray pos = jsonObject.getJSONArray("pos");
			if (pos.length()!=2){
				throw  new IllegalArgumentException();
			}
			arr[0] =pos.getDouble(0);
			arr[1] = pos.getDouble(1);
			p = new Vector(arr);			
		}
		catch(JSONException e){
			throw new IllegalArgumentException();
		}

		return  new MassLosingBody(id, v, a, p,m,lfa,lfr) ;
	}

}

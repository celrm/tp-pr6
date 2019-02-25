package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.IllegalArgumentException;

public abstract class Builder<T> {
	protected String typeTag;
	protected String desc;
	
	protected double[] jsonArrayTodoubleArray(JSONArray vel) {
		double[] arr = new double[vel.length()];
		for(int i = 0; i < vel.length(); ++i)
			arr[i] = vel.getDouble(i);
		return arr;
	}
	
	protected JSONObject createData() {
		return new JSONObject();
	}
	
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		JSONObject referencia = getBuilderInfo();
		if (!(info.has("type") && typeTag.equals(info.getString("type"))))
			return null;
		if (!info.has("data"))
			throw new IllegalArgumentException("no data");
		if (!referencia.getJSONObject("data").keySet().equals(info.getJSONObject("data").keySet()))
			throw new IllegalArgumentException("no keySets");
		
		T objeto = createTheInstance(info.getJSONObject("data"));
		return objeto;
	}

	public JSONObject getBuilderInfo() {
		JSONObject sol = new JSONObject();
		sol.put("type", typeTag);
		sol.put("data", createData());
		sol.put("desc", desc);
		return sol;
	}
	
	protected abstract T createTheInstance(JSONObject jsonObject);

}

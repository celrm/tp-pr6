package simulator.factories;

import org.json.JSONObject;
import java.lang.IllegalArgumentException;

public abstract class Builder<T> {
	protected String typeTag;
	protected String desc;
	
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		JSONObject referencia = getBuilderInfo();
		if (!(info.has("type") && typeTag.equals(info.getString("type"))))
			return null;
		if (!info.has("data"))
			throw new IllegalArgumentException("no data");
		if (!referencia.getJSONObject("data").keySet().equals(info.getJSONObject("data").keySet()))
			throw new IllegalArgumentException("no keySets");
		
		T objeto = createTheInstance(info);
		return objeto;
	}
	
	public abstract JSONObject getBuilderInfo();
	
	protected abstract T createTheInstance(JSONObject jsonObject);

}

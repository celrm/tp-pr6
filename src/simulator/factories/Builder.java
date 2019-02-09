package simulator.factories;

import org.json.JSONObject;
import java.lang.IllegalArgumentException;;

public abstract class Builder<T> {
	protected String typeTag;
	protected String desc;
	
public T createInstance(JSONObject info) throws IllegalArgumentException{
	
	JSONObject referencia = getBuilderInfo();
	if (info.has("type") && typeTag.equals(info.getString("type"))){
		if (info.has("data")){
			if (referencia.getJSONObject("data").keySet().equals(info.getJSONObject("data").keySet())){
				T objeto = createTheInstance(info);
				return objeto;
			}
			else throw new IllegalArgumentException();
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	return null;
}

public  abstract JSONObject getBuilderInfo();

protected abstract T createTheInstance(JSONObject jsonObject);

}

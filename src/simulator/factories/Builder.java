package simulator.factories;

import org.json.JSONObject;
import java.lang.IllegalArgumentException;;

public abstract class Builder<T> {
	protected String typeTag;
	protected String desc;
	
public T createInstance(JSONObject info) throws IllegalArgumentException{
	if (info.has("type")){
		if (typeTag.equals(info.getString("type"))){
			
		}
	}
}

public  abstract JSONObject getBulilderInfo();

protected abstract T createTheInstance(JSONObject jsonObject);

}

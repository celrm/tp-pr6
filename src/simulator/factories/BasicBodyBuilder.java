package simulator.factories;

import org.json.JSONObject;

import simulator.model.Body;

public class BasicBodyBuilder extends Builder <Body>{
	
	public BasicBodyBuilder(){
		typeTag = "basic";
		desc = "Basic body";
	}
	@Override
	public Body createInstance(JSONObject info) throws IllegalArgumentException {
		if (info.has(typeTag)){
			
		}
		return null;
	}

	@Override
	public JSONObject getBulilderInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

}

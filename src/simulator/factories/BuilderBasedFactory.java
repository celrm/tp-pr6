package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	private List<Builder<T>> availableBuilders;
	
	public BuilderBasedFactory(List<Builder<T>> builders){
		availableBuilders = builders;
	}
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		T objeto = null;
		try{
		for (Builder<T> c : availableBuilders){
			if (objeto == null){
				objeto = c.createInstance(info);
			}
		}
		}
		catch(IllegalArgumentException e){
			throw e;
		}
		return objeto;
	}

	@Override
	public List<JSONObject> getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

}

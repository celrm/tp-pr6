package simulator.factories;

import java.util.ArrayList;
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
		try {
			for (Builder<T> c : availableBuilders)
				if (objeto == null)
					objeto = c.createInstance(info);
		}
		catch(IllegalArgumentException e){
			throw e;
		}
		return objeto;
	}

	@Override
	public List<JSONObject> getInfo() {
		ArrayList<JSONObject> objetos = new ArrayList<>();
		for (Builder<T> c : availableBuilders) {
			objetos.add(c.getBuilderInfo());
		}
		return objetos;
		
	}

}

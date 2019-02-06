package simulator.factories;

import org.json.JSONObject;
import java.lang.IllegalArgumentException;;

public abstract class Builder<T> {
public abstract T createInstance(JSONObject info) throws IllegalArgumentException;
public abstract JSONObject getBulilderInfo();
}

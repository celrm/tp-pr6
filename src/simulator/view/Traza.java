package simulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Traza implements SimulatorObserver {
	Map<String,ArrayList<Vector>> l;
	
	private Controller _ctrl;
	
	public Traza(Controller ctr) {
		_ctrl = ctr;
		l = new TreeMap<>();
		_ctrl.addObserver(this);
	}
	
	public void printHistory() {
		for (Map.Entry<String, ArrayList<Vector>> entry : l.entrySet()) {
			String key = entry.getKey();
		    ArrayList<Vector> value = entry.getValue();
		    System.out.print(key);
		    System.out.print(": ");
		    for(int i = 0; i < value.size();++i) {
		    	if(i!=0)
				    System.out.print(", ");
			    System.out.print(value.get(i));
		    }
		    System.out.print("\n");
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dti, String gLawsDesc) {
		l = new TreeMap<>();
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dti, String gLawsDesc) {
		l = new TreeMap<>();
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		l.put(b.getId(), new ArrayList<>());
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		for(Body b : bodies) {
			l.get(b.getId()).add(b.getPosition());
		}
	}

	@Override
	public void onDeltaTimeChanged(double dti) {}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {}
	
}

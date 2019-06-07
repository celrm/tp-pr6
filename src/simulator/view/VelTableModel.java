package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class VelTableModel extends AbstractTableModel implements SimulatorObserver {
	private List<Body> _bodies;
	private List<Double> _bodiesVel;
	private int _steps;
	
	VelTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		_bodiesVel = new ArrayList<>();
		_steps = 1;
		ctrl.addObserver(this);
	}
	
	@Override
	public int getRowCount() {
		return _bodies.size();
	}
	@Override
	public int getColumnCount() {
		return 2;
	}
	@Override
	public String getColumnName(int column) {
		switch(column) {
		case 0: return "Id";
		case 1: return "Mean velocity";
		}
		return null;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(_bodies.size() == 0) return null;
		switch(columnIndex) {
		case 0: return _bodies.get(rowIndex).getId();
		case 1: return _bodiesVel.get(rowIndex);
		}
		return null;
	}
	private void getVels() {	
		for(int i = 0; i < _bodiesVel.size();++i) {
			Double x = _bodiesVel.get(i);
			_bodiesVel.set(i, ((x*_steps) + _bodies.get(i).getVelocity().magnitude())/(_steps+1));
			_steps++;
		}
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				_bodiesVel = new ArrayList<>();
				fireTableStructureChanged();
			}
		});
		getVels();
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				_bodiesVel = new ArrayList<>();
				fireTableStructureChanged();
			}
		});
		getVels();
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				_bodiesVel.add(b.getVelocity().magnitude());
				fireTableStructureChanged();
			}
		});
		getVels();
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
		});
		getVels();
	}
	@Override
	public void onDeltaTimeChanged(double dt) {}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {}
}

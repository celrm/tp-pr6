package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	
	private JLabel _currTime = new JLabel();	// for current time
	private JLabel _numOfBodies = new JLabel();	// for number of bodies
	private JLabel _currLaws = new JLabel();	// for gravity laws
	
	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
        
		JToolBar barra = new JToolBar();
        barra.setLayout(new BoxLayout(barra,BoxLayout.LINE_AXIS));
        
        barra.add(new JLabel("Time: "));
        barra.add(_currTime);
        barra.addSeparator(); // TODO separadores más amplios?
        barra.add(new JLabel("Bodies: "));
        barra.add(_numOfBodies);
        barra.addSeparator();
        barra.add(new JLabel("Laws: ")); // TODO no entiendo por qué hay dos descripciones de leyes distintas:
        barra.add(_currLaws);			// una en la propia ley y otra en el JSON
    	
		this.add(barra,BoxLayout.X_AXIS);
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_currTime.setText(new Double(time).toString());
		_numOfBodies.setText(new Integer(bodies.size()).toString());
		_currLaws.setText(gLawsDesc);
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_currTime.setText(new Double(time).toString());
		_numOfBodies.setText(new Integer(bodies.size()).toString());
		_currLaws.setText(gLawsDesc);
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_numOfBodies.setText(new Integer(bodies.size()).toString());
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		_currTime.setText(new Double(time).toString());
		_numOfBodies.setText(new Integer(bodies.size()).toString());		
	}
	@Override
	public void onDeltaTimeChanged(double dt) {}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		_currLaws.setText(gLawsDesc);
	}
}
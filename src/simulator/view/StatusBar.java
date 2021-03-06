package simulator.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

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
//		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
        
		JToolBar barra = new JToolBar();
        barra.setLayout(new BoxLayout(barra,BoxLayout.LINE_AXIS));
        barra.setFloatable(false); // Para que no se pueda sacar
//      barra.setUI(null);

        // Para que no tiemble        
        _currTime.setMinimumSize(new Dimension(70,20));
        _currTime.setMaximumSize(new Dimension(70,20));
        _currTime.setPreferredSize(new Dimension(70,20));

        _numOfBodies.setMinimumSize(new Dimension(15,20));
        _numOfBodies.setMaximumSize(new Dimension(15,20));
        _numOfBodies.setPreferredSize(new Dimension(15,20));


        _currLaws.setMinimumSize(new Dimension(300,20));
        _currLaws.setMaximumSize(new Dimension(300,20));
        _currLaws.setPreferredSize(new Dimension(300,20));
        
        
        barra.add(new JLabel(" Time: "));
        barra.add(_currTime);
        barra.addSeparator(new Dimension(30,30));
        barra.add(new JLabel("Bodies: "));
        barra.add(_numOfBodies);
        barra.addSeparator(new Dimension(30,30));
        barra.add(new JLabel("Laws: "));
        barra.add(_currLaws);

		barra.add(Box.createHorizontalGlue());

        barra.addSeparator();
    	
		this.add(barra,BoxLayout.X_AXIS);
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText(new Double(time).toString());
				_numOfBodies.setText(new Integer(bodies.size()).toString());
				_currLaws.setText(gLawsDesc);
			}
		});
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText(new Double(time).toString());
				_numOfBodies.setText(new Integer(bodies.size()).toString());
				_currLaws.setText(gLawsDesc);
			}
		});
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_numOfBodies.setText(new Integer(bodies.size()).toString());
			}
		});
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText(new Double(time).toString());
				_numOfBodies.setText(new Integer(bodies.size()).toString());
			}
		});	
	}
	@Override
	public void onDeltaTimeChanged(double dt) {}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currLaws.setText(gLawsDesc);
			}
		});
	}
}
package simulator.view;

import java.awt.Dimension;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {
	
	private Controller _ctrl;
	private boolean _stopped;
	
	ControlPanel(Controller ctr) { // coche perdido
		_ctrl = ctr;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	} 
	
	private void initGUI() {
		// TODO build the tool bar by adding buttons, etc.
		
		JToolBar barra = new JToolBar();
		
		JSpinner selectorPasos = new JSpinner(new SpinnerNumberModel(1000, 0, 5000, 500));
		selectorPasos.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
//				tf.setText("Has puesto en el spinner: " + selectorPasos.getValue());
			}
		});		
		selectorPasos.setSize(420, 200);
		barra.add(selectorPasos);
		
		JTextField dt = new JTextField(8);
		dt.setMaximumSize(new Dimension(150, 60));
		barra.add(dt);
		
		JButton load = new JButton();
		load.setActionCommand("load");
		load.setToolTipText("Load a file");
		load.addActionListener(this);
		load.setIcon(new ImageIcon("resources/icons/open.png"));
		barra.add(load);
		barra.addSeparator();
		
//		Es necesario completar el método initGUI() para crear todas las componentes del panel
//		(botones, selector de número de pasos, etc.). Puedes encontrar los iconos en el directorio
//		resources/icons. Como selector de pasos usa un JSpinner y para el área Delta-Time usa un
//		JTextField. Todos los botones han de tener tooltips para describir el efecto de pulsarlos.
	
		this.add(barra);
	}
	
	//cosas???
	
	@SuppressWarnings("unused")
	private void run_sim(int n) {
		if (n>0 && !_stopped ) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show the error in a dialog box
				// TODO enable all buttons
				_stopped = true;
				return;
			}
			
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					run_sim(n-1);
				}
			});
		} else {
		_stopped = true;
		// TODO enable all buttons
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	
}

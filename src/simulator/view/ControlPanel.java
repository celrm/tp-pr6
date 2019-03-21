package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {
	
	private Controller _ctrl;
	private boolean _stopped;

	private String campoDt = "";
	private int campoS = 0;
	
	ControlPanel(Controller ctr) { // coche perdido
		_ctrl = ctr;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	} 
	
	private void initGUI() {
		JToolBar barra = new JToolBar();
		
		JFileChooser fc = new JFileChooser();		
		JButton load = new JButton();
		load.setActionCommand("load");
		load.setToolTipText("Load a file");	
		load.setIcon(new ImageIcon("resources/icons/open.png"));	
		load.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
				int ret = fc.showOpenDialog(barra);
				if (ret == JFileChooser.APPROVE_OPTION) {
					_ctrl.reset();
					try {
						_ctrl.loadBodies(new FileInputStream(fc.getSelectedFile()));
						throw new FileNotFoundException();
					} catch (FileNotFoundException ex) {
						JOptionPane.showMessageDialog(barra, "File not found", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (JSONException ex) {
						JOptionPane.showMessageDialog(barra, "Corrupted file", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});		
		barra.add(load);
		
		JButton gl = new JButton();
		gl.setActionCommand("gl");
		gl.setToolTipText("Select a Gravity Law");		
		gl.setIcon(new ImageIcon("resources/icons/physics.png"));	
		gl.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 List<JSONObject> opciones = _ctrl.getGravityLawsFactory().getInfo();
	        	 String[] ops = new String[opciones.size()];
	        	 for(int i = 0; i < opciones.size(); ++i) {			// DUDA esto es horrible
	        		 ops[i] = opciones.get(i).getString("desc");
	        	 }
					String res = (String) JOptionPane.showInputDialog(
							barra,
							"Select a Gravity Law", "Select a Gravity Law",
							JOptionPane.PLAIN_MESSAGE, null, ops,ops[0]);
					for(JSONObject f : opciones) {
						if(f.get("desc").equals(res)) _ctrl.setGravityLaws(f);
					}
				}
		});		
		barra.add(gl);
		
		JButton play = new JButton();
		play.setActionCommand("run");
		play.setToolTipText("Run Simulation");		
		play.setIcon(new ImageIcon("resources/icons/run.png"));	
		play.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
//	        	 load.setEnabled(false);
//	        	 gl.setEnabled(false);
//	        	 play.setEnabled(false);
//	        	 exit.setEnabled(false); ?????
	        	 
	        	 _stopped = false;
	        	 
	        	 try { _ctrl.setDeltaTime(Integer.parseInt(campoDt)); }
	        	 catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(barra, "Not an integer", "Error",
								JOptionPane.ERROR_MESSAGE);
						}
	        	 
	        	 run_sim(campoS);
			}
		});		
		barra.add(play);
		
		JButton stop = new JButton();
		stop.setActionCommand("stop");
		stop.setToolTipText("Stop Simulation");		
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));	
		stop.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 _stopped = true;
	         }
		});		
		barra.add(stop);
		
		JSpinner selectorPasos = new JSpinner(new SpinnerNumberModel(1000, 0, 5000, 500));
		selectorPasos.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				campoS = (int) selectorPasos.getValue();
			}
		});		
//		selectorPasos.setSize(420, 200);
		barra.add(new JLabel("Steps:"));
		barra.add(selectorPasos);
		
		JTextField dt = new JTextField(8);
		dt.setMaximumSize(new Dimension(150, 60));
		dt.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 campoDt = dt.getText();
			}
		});
		barra.add(new JLabel("Delta-Time:"));
		barra.add(dt);
		
		JButton exit = new JButton();
		exit.setActionCommand("exit");
		exit.setToolTipText("Exit");		
		exit.setIcon(new ImageIcon("resources/icons/exit.png"));	
		exit.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 int n = JOptionPane.showOptionDialog(barra,
							"Are sure you want to quit?", "Quit",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
	        	 
	        	 if(n==0) System.exit(0);
	         }
		});		
		barra.add(exit);
		
	
		this.add(barra);
	}
	
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
//		load.setEnabled(true);
//   	 	gl.setEnabled(true);
//   	 	play.setEnabled(true); TODO acceder a los botones
//   	 exit.setEnabled(true); ?????
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		campoDt = new Double(dt).toString(); //  TODO acceder a los botones
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		campoDt = new Double(dt).toString();
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {}

	@Override
	public void onAdvance(List<Body> bodies, double time) {}

	@Override
	public void onDeltaTimeChanged(double dt) {
		campoDt = new Double(dt).toString();
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {}
	
}

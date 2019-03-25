package simulator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {
	
	private Controller _ctrl;
	private boolean _stopped;
	
	private JButton load = new JButton();
	private JButton gl = new JButton();
	private JButton play = new JButton();
	private JButton stop = new JButton();
	private JSpinner selectorPasos = new JSpinner(new SpinnerNumberModel(10000, 0, 50000, 500));
	private JTextField dt = new JTextField(8);
	private JButton exit = new JButton();
	
	ControlPanel(Controller ctr) { // coche perdido
		_ctrl = ctr;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
        setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        
		JToolBar barra = new JToolBar();
        barra.setLayout(new BoxLayout(barra,BoxLayout.LINE_AXIS));

		JFileChooser fc = new JFileChooser();
		load.setIcon(new ImageIcon("resources/icons/open.png"));	
		load.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
				int ret = fc.showOpenDialog(barra);
				if (ret == JFileChooser.APPROVE_OPTION) {
					_ctrl.reset();
					try {
<<<<<<< HEAD
						_ctrl.loadBodies(new FileInputStream(fc.getSelectedFile()));
=======
						InputStream in = new FileInputStream(fc.getSelectedFile());
						_ctrl.loadBodies(in);
>>>>>>> 25a190c4bd59b076278c2e9ed75fcd5bb004ce5d
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
		
		barra.addSeparator();
		
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
		
		barra.addSeparator();
				
		play.setIcon(new ImageIcon("resources/icons/run.png"));	
		play.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 try {
	        		 _ctrl.setDeltaTime(Double.parseDouble(dt.getText()));

		        	 load.setEnabled(false);
		        	 gl.setEnabled(false);
		        	 play.setEnabled(false);
		        	 exit.setEnabled(false); // este hay que bloquearlo?
		        	 
		        	 _stopped = false;
		        	 
	        		 run_sim((int) selectorPasos.getValue()); // qué error da?
	        	 }
	        	 catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(barra, "Wrong number", "Error",
								JOptionPane.ERROR_MESSAGE);
	        	 }
			}
		});
		barra.add(play);
			
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));	
		stop.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 _stopped = true;
	         }
		});		
		barra.add(stop);
		
		barra.add(new JLabel(" Steps: "));
		selectorPasos.setMinimumSize(new Dimension(100, 30));
		selectorPasos.setMaximumSize(new Dimension(100, 30));
		barra.add(selectorPasos);
		
		barra.add(new JLabel(" Delta-Time: "));
		dt.setMinimumSize(new Dimension(100, 30));
		dt.setMaximumSize(new Dimension(100, 30));
		barra.add(dt);

		barra.add(Box.createHorizontalGlue()); // Para que el exit esté a la dcha
		
		barra.addSeparator();
				
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
	
		this.add(barra,BoxLayout.X_AXIS);
	}
	
	private void run_sim(int n) {
		if (n>0 && !_stopped) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Exception while running simulation:\n" + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				
				_stopped = true;
				
				load.setEnabled(true);
		   	 	gl.setEnabled(true);
		   	 	play.setEnabled(true);
		   	 	exit.setEnabled(true);
		   	 	
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
			
			load.setEnabled(true);
	   	 	gl.setEnabled(true);
	   	 	play.setEnabled(true);
	   	 	exit.setEnabled(true);
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dti, String gLawsDesc) {
		dt.setText(new Double(dti).toString());
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dti, String gLawsDesc) {
		dt.setText(new Double(dti).toString());
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {}

	@Override
	public void onAdvance(List<Body> bodies, double time) {}

	@Override
	public void onDeltaTimeChanged(double dti) {
		dt.setText(new Double(dti).toString());
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {}
	
}

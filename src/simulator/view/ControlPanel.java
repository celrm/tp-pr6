package simulator.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	
	private JButton load = new JButton();
	private JButton gl = new JButton();
	private JButton play = new JButton();
	private JButton stop = new JButton();
	private JSpinner selectorDelay = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
	private JSpinner selectorPasos = new JSpinner(new SpinnerNumberModel(10000, 0, 50000, 500));
	private JTextField dt = new JTextField(8);
	private JButton exit = new JButton();
//	Añade en la clase ControlPanel un nuevo atributo llamado _thread del tipo
//	java.util.Thread, y hazlo volatile ya que será modificado desde distintos hilos.
	private volatile Thread _thread;
	
	ControlPanel(Controller ctr) {
		_ctrl = ctr;
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
						_ctrl.loadBodies(new FileInputStream(fc.getSelectedFile()));
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
	        	 for(int i = 0; i < opciones.size(); ++i) {
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
	        	 } catch (Exception ex) {
						JOptionPane.showMessageDialog(play, "Wrong number:\n" + ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);	        		 
	        	 }

		        load.setEnabled(false);
		        gl.setEnabled(false);
		        play.setEnabled(false);
		        exit.setEnabled(false);
		        
		        _thread = new Thread(new Runnable() {
		        	@Override
		        	public void run() {
//						(1) llama a run_sim con el número de pasos y el delay especificados en los 
//						correspondientes componentes JSpinner; 
						try {
				        	run_sim((int) selectorPasos.getValue(), new Long(selectorDelay.getValue().toString()));				
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(play, "Wrong number:\n" + ex.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}			
//						(2) habilita todos los botones, es decir, cuando termine la llamada a run_sim; y 			 				
			 			load.setEnabled(true);
			 		 	gl.setEnabled(true);
			 		 	play.setEnabled(true);
			 		 	exit.setEnabled(true);
//						(3) establece el atributo _thread a null.
			 		   	_thread = null;
					}		        		 
		        });
		        _thread.start();
	        }
		});
		barra.add(play);
			
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));	
		stop.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 if(_thread != null)
	        		 _thread.interrupt();
	         }
		});		
		barra.add(stop);
		
		barra.add(new JLabel(" Delay: "));
		selectorDelay.setMinimumSize(new Dimension(100, 30));
		selectorDelay.setMaximumSize(new Dimension(100, 30));
		barra.add(selectorDelay);
		
		barra.add(new JLabel(" Steps: "));
		selectorPasos.setMinimumSize(new Dimension(100, 30));
		selectorPasos.setMaximumSize(new Dimension(100, 30));
		barra.add(selectorPasos);
		
		barra.add(new JLabel(" Delta-Time: "));
		dt.setMinimumSize(new Dimension(100, 30));
		dt.setMaximumSize(new Dimension(100, 30));
		barra.add(dt);

		barra.add(Box.createHorizontalGlue()); // Para que el exit esté a la derecha
		
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
	
	private void run_sim(int n, long delay) {
		while (n>0 && (!Thread.currentThread().isInterrupted()) ) {
			// 1. execute the simulator one step, i.e., call method
			// _ctrl.run(1) and handle exceptions if any
			try {
				_ctrl.run(1);
			} catch (Exception e) {
//				Lo mismo se debe hacer cuando mostramos mensajes de error en el método run_sim
//				Esto es necesario ya que ahora se ejecuta en un hilo distinto al hilo de Swing. 
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JOptionPane.showMessageDialog(play, "Exception while running simulation:\n" + e.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				});
				return;
			}
			// 2. sleep the current thread for ’delay’ milliseconds
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
//				Por lo tanto, en tal caso, se debe interrumpir nuevamente
//				el hilo actual al capturar la excepción correspondiente para salir del bucle (o simplemente
//				salir del método con return).
				return;
			}
			n--;
		}
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dti, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dt.setText(new Double(dti).toString());
			}
		});
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dti, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dt.setText(new Double(dti).toString());
			}
		});
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {}

	@Override
	public void onAdvance(List<Body> bodies, double time) {}

	@Override
	public void onDeltaTimeChanged(double dti) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				dt.setText(new Double(dti).toString());
			}
		});
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {}
	
}

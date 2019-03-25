package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	Controller _ctrl;
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setSize(600, 400);
		this.setMinimumSize(new Dimension(600, 400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // TODO show question
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel pancontrol = new ControlPanel(_ctrl);
		this.getContentPane().add(pancontrol, BorderLayout.PAGE_START); // (1) coloca el panel de control en el PAGE_START del panel mainPanel;

		JPanel statb = new StatusBar(_ctrl);
		this.getContentPane().add(statb, BorderLayout.PAGE_END); // (2) coloca la barra de estado en el PAGE_END del mainPanel;

		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS)); // (3) crea un nuevo panel que use BoxLayout (y BoxLayout.Y_AXIS) y colócalo en el CENTER de mainPanel. 
		
		JPanel tabla = new BodiesTable(_ctrl);
		JComponent view = new Viewer(_ctrl);	
		
		// Añade la tabla de cuerpos y el viewer en este panel.
		centro.add(tabla); 
		centro.add(view);
		
		this.getContentPane().add(centro, BorderLayout.CENTER);
		
		this.setVisible(true);
		
//		TODO
//		Para controlar el tamaño inicial de cada componente puedes usar el método setPrefe-
//		rredSize. También necesitarás hacer visible la ventana, etc.
		
	}
}
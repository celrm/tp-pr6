import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class EjemploChangeEvent extends JFrame {

	public EjemploChangeEvent() {
		super("Ejemplo para mostrar el evento ChangeEvent");
		this.setSize(520, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Si haces click cambias el texto");
		boton.addActionListener(new MiActionListener());
		boton.addChangeListener(new MiChangeListener());
		this.getContentPane().add(boton);
		this.setVisible(true);
	}

	public class MiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// quiero acceder al objeto que ha generado el evento para
			// modificarlo, necesitamos Object getSource();
			((JButton) event.getSource()).setText("me has pulsado!");
		}
	}

	public class MiChangeListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			System.out.println("Has pasado el ratón!");
			//El botón ocupa todo el espacio de la ventana
			//si sólo hay uno y no pones layout (lo veremos después).
			//Observar que se activa cuando paso el ratón por la 
			//ventana que contiene el botón.
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploChangeEvent();
				
			}
		});
	}
}


//autor del ejemplo : vicky
//creando ventana de diálogo con mis propios botones y mi Icon

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class EjemploJOptionPane extends JFrame {

	public EjemploJOptionPane() {
		super("Mi primer ejemplo de JOptionPane");
		initGUI();
	}

	private void initGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		JLabel label0 = new JLabel(" ");
		mainPanel.add(label0);
		
		JButton boton1 = new JButton("Ventana informativa error");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Saca ventana con un texto informando de un error
				// y un botón para cerrar la ventana.
				JOptionPane.showMessageDialog(EjemploJOptionPane.this,
						"Mi mensaje de error", "Mi título",
						JOptionPane.ERROR_MESSAGE);
				// En función de la constante que yo ponga
				// (JOptionPane.ERROR_MESSAGE)
				// sale un tipo de ventana y un tipo de icono.
			}
		});
		boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(boton1);
		
		JLabel label1 = new JLabel(" ");
		mainPanel.add(label1);
		
		JButton boton2 = new JButton("Ventana sencilla de opciones ");
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int n = JOptionPane.showOptionDialog(EjemploJOptionPane.this,
						"Are sure you want to quit?", "Quit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				// Tipo de ventana: JOptionPane.QUESTION_MESSAGE
				// Los tipos posibles de botones son: (probad con ellos)
				// DEFAULT_OPTION
				// YES_NO_OPTION
				// YES_NO_CANCEL_OPTION
				// OK_CANCEL_OPTION
				
				//hay 2 formas de salir de la ventana
				//se utiliza en la práctica para que el usuario
				//confirme qu equiere salir de la aplicación
				if (n == 0) {
					System.out.println("dijiste si");
					System.exit(0);
				}
				if (n == 1) {
					System.out.println("dijiste no");
				}
			}
		});

		boton2.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(boton2);
		
		JLabel label2 = new JLabel(" ");
		mainPanel.add(label2);

		JButton boton3 = new JButton(
				"Ventana con mis propios botones ");
		boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object[] options = { "opcion1", "opcion2", "opcion3" };
				int n = JOptionPane.showOptionDialog(EjemploJOptionPane.this,
						"Aquí van mis propios botones", "Los míos!!!!!",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE, new ImageIcon(this
								.getClass().getResource("OK-icon.png")),
						options, options[0]);
			}
		});
		boton3.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(boton3);
		pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploJOptionPane();
			}
		});
	}
}

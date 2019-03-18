//autor vicky
//Muestra ventanas secillas de diálogo con un texto y un botón .
//Utilizamos el método showMessageDialog() para mostrarlas.
//Observar como va cambiando el icono de las ventanas de diálogo
//según el tipo de ventana que sea.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class EjemploJRadioButtonYJOptionPane extends JFrame {
	JRadioButton error_message;
	JRadioButton information_message;
	JRadioButton warning_message;
	JRadioButton question_message;
	JRadioButton plain_message;

	public EjemploJRadioButtonYJOptionPane() {
		super("Mostraremos los distintos tipos de ventanas sencillas");
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		this.setContentPane(mainPanel);
		this.setLocation(50, 50);
		this.setSize(900, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AccionRadios actRadios = new AccionRadios();//objeto manejador

		error_message = new JRadioButton("ERROR_MESSAGE");
		error_message.addActionListener(actRadios);
		error_message.setActionCommand("E_M");

		information_message = new JRadioButton("INFORMATION_MESSAGE");
		information_message.addActionListener(actRadios);
		information_message.setActionCommand("I_M");

		warning_message = new JRadioButton("WARNING_MESSAGE");
		warning_message.addActionListener(actRadios);
		warning_message.setActionCommand("W_M");

		question_message = new JRadioButton("QUESTION_MESSAGE");
		question_message.addActionListener(actRadios);
		question_message.setActionCommand("Q_M");

		plain_message = new JRadioButton("PLAIN_MESSAGE");
		plain_message.addActionListener(actRadios);
		plain_message.setActionCommand("P_M");

		error_message.setSelected(true);

		ButtonGroup grupoBotones = new ButtonGroup();
		grupoBotones.add(error_message);
		grupoBotones.add(information_message);
		grupoBotones.add(warning_message);
		grupoBotones.add(question_message);
		grupoBotones.add(plain_message);

		//Al panel se añaden uno a uno
		mainPanel.add(error_message);
		mainPanel.add(information_message);
		mainPanel.add(warning_message);
		mainPanel.add(question_message);
		mainPanel.add(plain_message);

		this.setVisible(true);
	}

	private class AccionRadios implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals("E_M")) {
				JOptionPane.showMessageDialog(
						EjemploJRadioButtonYJOptionPane.this,
						"Mi mensaje ", "Mi título de Dialog",
						JOptionPane.ERROR_MESSAGE);
			} else if (event.getActionCommand().equals("I_M")) {
				JOptionPane.showMessageDialog(
						EjemploJRadioButtonYJOptionPane.this,
						"Mi mensaje", "Mi título de Dialog",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (event.getActionCommand().equals("W_M")) {
				JOptionPane.showMessageDialog(
						EjemploJRadioButtonYJOptionPane.this,
						"Mi mensaje", "Mi título de Dialog",
						JOptionPane.WARNING_MESSAGE);
			} else if (event.getActionCommand().equals("Q_M")) {
				JOptionPane.showMessageDialog(
						EjemploJRadioButtonYJOptionPane.this,
						"Mi mensaje", "Mi título de Dialog",
						JOptionPane.QUESTION_MESSAGE);
			} else if (event.getActionCommand().equals("P_M")) {
				JOptionPane.showMessageDialog(
						EjemploJRadioButtonYJOptionPane.this,
						"Mi mensaje", "Mi título de Dialog",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploJRadioButtonYJOptionPane();
			}
		});
	}
}

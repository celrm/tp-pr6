package pintarEnSwing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class MiVentana extends JFrame {
	private Viewer componentePintar;

	public MiVentana() {
		super("Mi primera Ventana");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		componentePintar = new Viewer();
		mainPanel.add(componentePintar, BorderLayout.CENTER);
		
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MiVentana();
			}
		});
	}
}

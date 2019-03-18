import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploBoxLayout extends JFrame {
	public EjemploBoxLayout() {
		super("Mi ventanita");
		this.setSize(600,200);
		JPanel mainPanel = new JPanel();
		
		//probarlo también con Y_AXIS
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		this.getContentPane().add(mainPanel);
		JButton boton1 = new JButton("hola");
		JButton boton2 = new JButton("holaaa");
		JButton boton3 = new JButton("holaaaaa");
		mainPanel.add(boton1);
		mainPanel.add(boton2);
		mainPanel.add(boton3);
		
		//probar con esto y sin esto:
		//this.pack();
		//ajusta el tamaño a los componentes y anula el this.setSize(600,200);
		
		this.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploBoxLayout();
				
			}
		});
	}

}

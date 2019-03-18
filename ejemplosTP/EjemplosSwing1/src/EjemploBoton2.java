import javax.swing.*;
import java.awt.event.*;


public class EjemploBoton2 extends JFrame {

	public EjemploBoton2() {
		super("Mi primera ventana - EjemploBoton2");
		this.setSize(600, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Pulsa para saludar");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Hola Mundo2!");
			}
		});
		this.getContentPane().add(boton);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// ...
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploBoton2();
			}
		});
	}
}


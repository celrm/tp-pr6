import javax.swing.*;
import java.awt.event.*;
//nuestro primer botón, sin clase anónima para el manejador
public class EjemploBoton1 extends JFrame {

	public EjemploBoton1() {
		super("Mi primera ventana - EjemploBoton1");
		this.setSize(620, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Pulsa para saludar");
		boton.addActionListener(new MiActionListener());
		this.getContentPane().add(boton);
		this.setVisible(true);
	}

	public class MiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Hola Mundo1!");
		}
	}

	public static void main(String[] args) {
		// ...	
		SwingUtilities.invokeLater(new Runnable() {		
			public void run() {
				new EjemploBoton1();
				
			}
		});
	}
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploFlowLayout extends JFrame {

	public EjemploFlowLayout() {
		super("Ejemplo con FlowLayout:");
		
		this.setSize(700, 200);//probarlo también con this.setSize(320, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton1 = new JButton("Pulsa para saludar");
		JButton boton2 = new JButton("Pulsa para cerrar la aplicacción");
		
		
		this.getContentPane().setLayout(new FlowLayout()); //SALE CENTRADO
		//Probarlo también sin layout. Qué pasa? Que sólo saca un botón.
		//Si quiero sacar más de un componente hay que poner el layout.
		//Probarlo también así:
		//this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		//this.getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		this.getContentPane().add(new JLabel("Ventana de saludo"));
		this.getContentPane().add(boton1);
		this.getContentPane().add(new JLabel("otra etiqueta"));
		this.getContentPane().add(boton2);
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("Cómo están ustedes????????????");
			}});
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose(); //cerramos la aplicación desde un botón,
						   //termina y libera recursos.
			}});
		this.setVisible(true);
		}

	public static void main(String []args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploFlowLayout();
			}});
	}
}
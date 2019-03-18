package pintarEnSwing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class VentanaCara extends JFrame {

	private boolean sonrie = true;

	public VentanaCara() {
		super("Aquí! pasando el rato");
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);

		JButton boton = new JButton("Cambia esa cara !!!!");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				sonrie = !sonrie;
				repaint();
			}
		});
		boton.setPreferredSize(new Dimension(30, 40));
		mainPanel.add(boton, BorderLayout.SOUTH);

		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D gr = (Graphics2D) g;
		
		// Dibujo el contorno de la cara
		gr.setColor(Color.BLACK);
		gr.fillOval(105, 70, 100, 100);

		// Dibujo de los ojos
		gr.setColor(Color.GREEN);
		gr.fillOval(125, 100, 10, 10);
		gr.fillOval(175, 100, 10, 10);

		// Dibujo de la nariz
		gr.drawLine(150, 100, 150, 125);

		// Dibujo de la boca

		if (sonrie)
			gr.drawArc(118, 125, 75, 30, 180, 180);

		else
			gr.drawArc(118, 125, 75, 30, 180, -180);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VentanaCara();
			}
		});
	}
}

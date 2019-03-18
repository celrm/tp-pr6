package pintarEnSwing;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

//El sistema de coordenadas de un contenedor tiene su origen en su esquina 
//superior izquierda.
//Las abscisas se incrementan hacia la derecha y las ordenadas hacia abajo.
//En general, el dibujo de una figura (rectángulo, elipse, rectángulo redondo, 
//etc.) se realiza dando las coordenadas de la esquina superior izquierda 
//de un rectángulo imaginario que la contiene.

public class Viewer extends JComponent {
	public Viewer() {
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.red, 2), "Viewer",
				TitledBorder.LEFT, TitledBorder.TOP));
		repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;

		gr.setColor(Color.blue);
		gr.drawLine(50, 70, 80, 140); // coord. de pto inicial a pto final
		gr.drawRect(150, 70, 50, 70);
		gr.drawRoundRect(250, 70, 50, 70, 16, 16);
		gr.drawOval(350, 70, 50, 70);

		gr.setColor(Color.GREEN);
		gr.fillRect(150, 270, 50, 70);
		gr.fillRoundRect(250, 270, 50, 70, 16, 16);
		gr.fillOval(375, 270, 50, 50);
		gr.fillOval(475, 270, 70, 40);

		gr.setColor(Color.RED);
		gr.drawString("estoy pintando mi string", 50, 600);
		
		gr.setColor(Color.BLACK);
		int ancho=getWidth()/2;//coge el ancho del componente
		int alto=getHeight()/2;//coge el alto del componente
		//ahora pinto un circulo en el centro
		gr.fillOval(ancho,alto, 5, 5);	

	}
}

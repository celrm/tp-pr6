import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploGridLayout extends JFrame {

	public EjemploGridLayout() {
		super("Ejemplo con GridLayout:  ");
		this.setSize(450, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int filas = 3;
		int cols = 4;

		this.getContentPane().setLayout(new GridLayout(filas, cols, 25, 5));
		//el tercer argumento es el espacio entre columnas
		//el cuarto argumento es el espacio entre filas

		// para hacerlo sin centrar, sería así:
		// for (int i = 0; i < filas; i++) {
		// for (int j = 0; j < cols; j++) {
		// JLabel lbl = new JLabel("(" + i + ", "
		// + j + ")");
		// lbl.setBackground(Color.blue);
		// lbl.setOpaque(true);
		// this.getContentPane().add(lbl);
		// }
		// }

		// para hacerlo centrado:
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < cols; j++) {
				JPanel p = new JPanel();
				p.setAlignmentX(CENTER_ALIGNMENT);
				p.add(new JLabel("(" + i + ", "
						+ j + ")"));
				p.setBackground(Color.blue);
				p.setOpaque(true);
				this.getContentPane().add(p);
			}
		}
		this.setVisible(true);
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploGridLayout();
			}
		});
	}
}

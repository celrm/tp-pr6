import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EjemploBorderLayout extends JFrame {

	public EjemploBorderLayout() {
		super("Ejemplo con BorderLayout: ");
		this.setSize(700, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.getContentPane().setLayout(new BorderLayout());

		JLabel lnorth = new JLabel("Región Norte");
		lnorth.setBackground(Color.BLUE);
		//lnorth.setOpaque(true); es para
		//que sea visible el cambio de color,
		//si lo pongo a false sale en gris.
		lnorth.setOpaque(true);
		this.getContentPane().add(lnorth, BorderLayout.NORTH);

		JLabel least = new JLabel("región este");
		least.setBackground(Color.GREEN);
		least.setOpaque(true);
		this.getContentPane().add(least, BorderLayout.LINE_END);

		JLabel lsouth = new JLabel("Región sur");
		lsouth.setBackground(Color.RED);
		lsouth.setOpaque(true);
		this.getContentPane().add(lsouth, BorderLayout.SOUTH);

		JLabel lwest = new JLabel("región oeste");
		lwest.setBackground(Color.YELLOW);
		lwest.setOpaque(true);
		this.getContentPane().add(lwest, BorderLayout.WEST);

		JLabel lcenter = new JLabel("región centro");
		lcenter.setBackground(Color.GRAY);
		lcenter.setOpaque(true);
		this.getContentPane().add(lcenter, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new EjemploBorderLayout();
			}
		});
	}
}

